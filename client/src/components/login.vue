<template>
    <div class="login">

        <h1 style="text-align: center">登录</h1>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="用户名：" prop="name">
                <el-input v-model="form.name" placeholder="请输入用户名"></el-input>
            </el-form-item>

            <el-form-item label="密码：" prop="passwd">
                <el-input v-model="form.passwd" placeholder="请输入密码" show-password clearable></el-input>
            </el-form-item>

            <el-form-item label-width="0px">
                <el-button size="medium" style="border-color: #ffffff; margin-right: 43%; left: 0px;" @click="clickRegister">创建账户</el-button>
                <el-button type="primary" size="medium" :loading = "isLoading" @click="ClickLogin" >{{text}}</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>

<script>
    export default {
        name: "login",
        data() {
            return {
                state: -1,
                group: 0,
                text: "登录",
                isDisabled: false,
                isLoading: false,
                form: {
                    name: '',
                    passwd: '',
                },
                rules: {
                    name: [
                        {
                            required: true, message: "请输入用户名", trigger: 'blur'
                        },
                    ],
                    passwd: [
                        {
                            required: true, message: "请输入密码", trigger: 'blur'
                        },
                        {
                            min: 6, message: "密码必须大于6", trigger: 'blur'
                        }
                    ]
                },
            }
        },
        methods: {
            ClickLogin() {
                // this.isLoading = !this.isLoading;
                // this.text = "";
                if (this.isLoading) {
                    this.text = ""
                }
                this.$refs["form"].validate((valid) => {
                    if (valid) {
                        // alert('submit!');

                        this.$axios({
                            url: this.$loginUrl,
                            method: 'post',
                            data: {
                                user: this.form.name,
                                passwd: this.$md5(this.form.passwd + this.$salt),
                            },

                            transformRequest: [function (data) {
                                let ret = '';
                                for (let it in data) {
                                    ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                                }
                                return ret
                            }],
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                            // headers: {
                            //     'Content-Type': 'application/x-www-form-urlencoded'
                            // }
                        }).then(res => {
                            this.text = "";
                            this.isLoading = true;
                            this.state = res.data.state;
                            this.group = res.data.group;
                            this.$store.state.userName = this.form.name;
                            this.$store.state.passwd = this.form.passwd;
                            this.$store.state.token = res.data.token;
                            this.$store.state.group = res.data.group;
                            this.loginProcess();
                        })
                    } else {
                        this.text = "登录";
                        this.isLoading = false;
                        // console.log('error submit!!');
                        return false;
                    }
                });
            },

            loginProcess() {
                if (this.state === 0) {
                    if (this.group === 0) {
                        this.$router.push('/mainFrame')
                    }

                    if (this.group === 1) {
                        this.$router.push('/manager')
                    }
                } else {
                    this.text = "登录";
                    this.isLoading = false;

                    if (this.state === 1) {
                        this.$alert('账号不存在！', '警告', {
                            confirmButtonText: '确定',
                            callback: {
                            }
                        });
                    }

                    if (this.state === -1) {
                        this.$alert('密码错误！', '警告', {
                            confirmButtonText: '确定',
                            callback: {
                            }
                        });
                    }
                }
            },
            clickRegister() {
                this.$router.push("/register")
            }
        },




    }
</script>

<style scoped>

    .login {
        margin: auto;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 20rem;
        position: absolute;
        background-color: white;
        padding: 20px 20px 10px 20px;
        border-radius: 10px;
        box-shadow: 0px 15px 25px 0px rgba(0, 0, 0, 0.11);
    }


</style>

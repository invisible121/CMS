<template>
    <div class="register">

        <h1 style="text-align: center">注册</h1>
        <el-form ref="registerForm" :model="registerForm" :rules="rules" label-width="100px">
            <el-form-item label="用户名：" prop="name">
                <el-input v-model="registerForm.name" placeholder="请输入用户名"></el-input>
            </el-form-item>

            <el-form-item label="密码：" prop="passwd">
                <el-input v-model="registerForm.passwd" placeholder="请输入密码" show-password clearable></el-input>
            </el-form-item>

            <el-form-item label="再次密码：" prop="passwdagain">
                <el-input v-model="registerForm.passwdagain" placeholder="请确认密码" show-password clearable></el-input>
            </el-form-item>

            <el-form-item label-width="0px">
                <el-button size="medium" style="border-color: #ffffff; margin-right: 43%; left: 0px;" @click="clickLogin">登录</el-button>
                <el-button type="primary" size="medium" :loading = "isLoading"  @click="clickRegister">{{text}}</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>

<script>
    export default {
        name: "register",
        data() {
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.registerForm.passwd) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };

            return {
                state: -1,
                text: "创建账号",
                isDisabled: false,
                isLoading: false,
                registerForm: {
                    name: '',
                    passwd: '',
                    passwdagain: ''
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
                    ],
                    passwdagain: [
                        {
                            required: true, message: "请输入密码", trigger: 'blur'
                        },
                        {
                            min: 6, message: "密码必须大于6", trigger: 'blur'
                        },
                        {
                            validator: validatePass2, trigger: 'blur'
                        }
                    ]
                },
            }
        },

        methods: {
            clickRegister() {
                if (this.isLoading) {
                    this.text = ""
                }

                this.$refs["registerForm"].validate((valid) => {
                    if(valid) {
                        this.$axios({
                            url: this.$registerUrl,
                            method: 'post',
                            data: {
                                user: this.registerForm.name,
                                passwd: this.$md5(this.registerForm.passwd + this.$salt)
                            },
                            transformRequest: [function (data) {
                                let ret = '';
                                for (let it in data) {
                                    ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                                }
                                return ret
                            }],
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                        }).then(res => {
                            if (res.data.state === 0) {
                                this.$axios({
                                    url: this.$loginUrl,
                                    method: 'post',
                                    data: {
                                        user: this.registerForm.name,
                                        passwd: this.$md5(this.registerForm.passwd + this.$salt)
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
                                    this.state = res.data.state;
                                    this.$store.state.userName = this.registerForm.name;
                                    this.$store.state.passwd = this.registerForm.passwd;
                                    this.$store.state.token = res.data.token;
                                    this.$store.state.group = res.data.group;
                                    this.$router.push('/mainFrame')
                                })
                            } else {
                                alert("账号已存在！")
                            }
                        })
                    }else  {
                        this.text = "创建账户";
                        this.isLoading = false;
                        // console.log('error submit!!');
                        return false;
                    }
                })
            },

            loginProcess() {
                if (this.state === 0) {
                    this.$router.push('/mainFrame')
                } else {
                    this.text = "登录";
                    this.isLoading = false;
                }
            },
            clickLogin() {
                this.$router.push("/login")
            }
        }
    }
</script>

<style scoped>

    .register {
        margin: auto;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 25rem;
        position: absolute;
        background-color: white;
        padding: 20px 20px 10px 20px;
        border-radius: 10px;
        box-shadow: 0px 15px 25px 0px rgba(0, 0, 0, 0.11);
    }

</style>

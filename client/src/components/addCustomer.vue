<template>

    <el-form style="left: 20%;" ref="cusForm" :model="cusForm" :rules="rules" label-position="left" label-width="80px">
        <el-form-item label="客户名称" prop="name">
            <el-input v-model="cusForm.name"></el-input>
        </el-form-item>
        <el-form-item label="客户电话" prop="tel">
            <el-input v-model="cusForm.tel"></el-input>
        </el-form-item>
        <el-form-item label="客户地址" prop="address">
            <el-input v-model="cusForm.address"></el-input>
        </el-form-item>
        <el-form-item label="客户传真" prop="fax">
            <el-input v-model="cusForm.fax"></el-input>
        </el-form-item>
        <el-form-item label="客户邮箱" prop="mail">
            <el-input v-model="cusForm.mail"></el-input>
        </el-form-item>
        <el-form-item label="银行名称" prop="bank">
            <el-input v-model="cusForm.bank"></el-input>
        </el-form-item>
        <el-form-item label="银行账号" prop="account">
            <el-input v-model="cusForm.account"></el-input>
        </el-form-item>
        <el-form-item>
            <el-col :span="15">
                <el-button type="primary" @click="postCustomer">确定</el-button>
            </el-col>
            <el-col :span="9">
                <el-button type="danger" @click="resetJson">重置</el-button>
            </el-col>
        </el-form-item>
    </el-form>

</template>

<script>
    export default {
        name: "addCustomer",
        data() {
            return {
                cusForm: {
                    name: '',
                    tel: '',
                    address: '',
                    fax: '',
                    mail: '',
                    bank: '',
                    account: '',
                },

                rules: {
                    name: {
                        required: true, message: "请输入客户姓名", trigger: 'blur'
                    },

                }
            }
        },
        methods: {
            cleanJson(json) {
                for (let draftFormKey in json) {
                    json[draftFormKey] = ''
                }
                return json;
            },

            postCustomer() {

                this.$refs["cusForm"].validate((valid) => {
                    if (valid) {
                        this.$axios({
                            url: this.$url + "/customer/addCustomer",
                            method: 'post',
                            data: {
                                token: this.$store.state.token,
                                name: this.cusForm.name,
                                address: this.cusForm.address,
                                tel: this.cusForm.tel,
                                fax: this.cusForm.fax,
                                mail: this.cusForm.mail,
                                bank: this.cusForm.bank,
                                account: this.cusForm.account
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
                            if (res.data.state === -1) {
                                this.$notify({
                                    title: '失败',
                                    message: '添加客户失败！',
                                    type: "error"
                                })
                            }

                            if (res.data.state === 0) {
                                this.$notify({
                                    title: '成功',
                                    message: '成功添加客户！',
                                    type: "success"
                                })
                                this.cusForm = this.cleanJson(this.cusForm)
                            }
                        })
                    }
                });
            },


            resetJson() {
                this.cusForm = this.cleanJson(this.cusForm)
            }
        }
    }
</script>

<style scoped>

</style>

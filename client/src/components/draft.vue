<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            起草合同
        </div>

        <div>
            <el-form ref="draftForm" :model="draftForm" :rules="rule" label-position="left" label-width="85px">
                <el-form-item label="合同名称:" prop="name">
                    <el-input v-model="draftForm.name"></el-input>
                </el-form-item>
                <el-form-item label="客户:" prop="userName">
                    <el-select v-model="draftForm.userName" clearable placeholder="请选择">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
<!--                    <el-input style="width: 70%" v-model="draftForm.userName"></el-input>-->
                    <el-button @click="dialogVisible=true">添加客户</el-button>
                </el-form-item>
                <el-form-item label="活动时间" prop="date">
                    <el-date-picker
                            v-model="draftForm.date"
                            type="daterange"
                            align="right"
                            unlink-panels
                            range-separator="至"
                            value-format="yyyy-MM-dd"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="合同内容" prop="info">
                    <el-input type="textarea" :autosize="{minRows:3, maxRows:6}" v-model="draftForm.info"></el-input>
                </el-form-item>


                <el-form-item>
                    <el-row>
                        <el-col :span="15">
                            <el-button type="primary" @click="postContract">确定</el-button>
                        </el-col>

                        <el-col :span="9">
                            <el-button type="danger" @click="resetContract">重置</el-button>
                        </el-col>
                    </el-row>
                </el-form-item>

            </el-form>
        </div>

        <el-dialog @close="closeDlg" center width="35%" title="添加客户" :visible.sync="dialogVisible">
            <add-customer></add-customer>
        </el-dialog>

    </el-card>
</template>

<script>
    import AddCustomer from "@/components/addCustomer";
    export default {
        name: "draft",
        components: {AddCustomer},
        data() {

            const generateOpt = () => {
                const data = [];

                this.$axios({
                    url: this.$url + "/customer/selectAllCustomer",
                    method: 'post',
                    data: {
                        token: this.$store.state.token,
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

                    res.data.customers.forEach((item, index) => {
                        data.push({
                            value: item.id,
                            label: item.name,
                            index: index
                        });
                    })

                });

                return data;
            };


            return {

                dialogVisible: false,

                draftForm: {
                    name: '',
                    userName: '',
                    date: '',
                    info: '',
                },

                nullContract: {
                    name: '',
                    userName: '',
                    date: '',
                    info: '',
                },

                options: generateOpt(),
                value: '',

                customerValue: '',

                rule: {
                    name: {
                        required: true, message: '请输入合同名称', trigger: 'blur'
                    },

                    userName: {
                        required: true, message: '请输入客户姓名', trigger: 'blur'
                    },
                    date: {
                        required: true, message: '请选择时间', trigger: 'blur'
                    },
                    info: {
                        required: true, message: '请输入合同内容', trigger: 'blur'
                    }
                }
            }
        },

        methods: {
            resetContract() {
                this.$confirm("此操作将清空该合同，是否继续？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.draftForm = this.cleanJson(this.draftForm);
                    this.$notify({
                        title: '成功',
                        message: '清空成功！',
                        type: "success"
                    })
                })
            },


            closeDlg() {
                const generateOpt = () => {
                    const data = [];

                    this.$axios({
                        url: this.$url + "/customer/selectAllCustomer",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
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

                        res.data.customers.forEach((item, index) => {
                            data.push({
                                value: item.id,
                                label: item.name,
                                index: index
                            });
                        })

                    });

                    return data;
                };

                this.options =  generateOpt();
            },

            postContract() {

                this.$refs["draftForm"].validate((valid) => {
                    if (valid) {

                        // eslint-disable-next-line no-console
                        console.log(this.draftForm);
                        this.$axios({
                            url:this.$url + "/contract/addContract",
                            method: 'post',
                            data: {
                                token: this.$store.state.token,
                                contract_name: this.draftForm.name,
                                customer_id: this.draftForm.userName,
                                dateFrom: this.draftForm.date[0],
                                dateTo: this.draftForm.date[1],
                                content: this.draftForm.info
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
                                this.$notify({
                                    title: '成功',
                                    message: '成功添加合同！',
                                    type: "success"
                                })

                                this.draftForm = this.cleanJson(this.draftForm)
                            }
                            if (res.data.state === -1) {
                                this.$notify({
                                    title: '失败',
                                    message: '添加合同失败！',
                                    type: "error"
                                })
                            }
                            // eslint-disable-next-line no-console
                            console.log(res);
                        })
                    } else {
                        this.$notify({
                            title: '失败',
                            message: '提交失败！',
                            type: "error"
                        })
                    }
                })


            },

            cleanJson(json) {
                for (let draftFormKey in json) {
                    json[draftFormKey] = ''
                }
                return json;
            }
        }
    }
</script>

<style scoped>


    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 50%;
        left: 20%;
        top: 15%;
        position: relative;
    }


</style>
<style>
    .el-menu--horizontal .el-menu-item:not(.is-disabled):focus {
        color: #ffffff;
    }

    .el-menu--horizontal>.el-submenu.is-active .el-submenu__title {
        color: #ffffff;
    }

    .el-menu--horizontal .el-menu .el-menu-item.is-active, .el-menu--horizontal .el-menu .el-submenu.is-active>.el-submenu__title {
        color: #FFFFFF;
    }
</style>

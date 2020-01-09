<template>
<!--    <el-card class="box-card">-->
<!--        <div slot="header" class="clearfix">-->
<!--            修改合同-->
<!--        </div>-->

        <div>
            <el-form ref="draftForm" :model="draftForm" rules="rule" label-position="left" label-width="80px">
                <el-form-item label="合同名称:" prop="name">
                    <el-input v-model="draftForm.name"></el-input>
                </el-form-item>
                <el-form-item label="客户:" prop="userName">
                    <el-input v-model="draftForm.userName"></el-input>
                </el-form-item>
                <el-form-item label="活动时间" prop="date">
                    <el-date-picker
                            v-model="draftForm.data"
                            type="daterange"
                            align="right"
                            unlink-panels
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="合同内容" prop="info">
                    <el-input type="textarea" :autosize="{minRows:3, maxRows:6}" v-model="draftForm.info"></el-input>
                </el-form-item>

                <el-form-item label="修改意见" prop="commit_comment">
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
<!--    </el-card>-->
</template>

<script>
    export default {
        name: "EditContract",
        data() {
            return {
                draftForm: {
                    name: '',
                    userName: '',
                    data: '',
                    info: '',
                },

                nullContract: {
                    name: '',
                    userName: '',
                    data: '',
                    info: '',
                },

                rule: {
                    name: {
                        required: true, message: '请输入合同名称', trigger: 'blur'
                    },

                    userName: {
                        required: true, message: '请输入客户姓名', trigger: 'blur'
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

            postContract() {

                this.$refs["draftForm"].validate((valid) => {
                    if (valid) {
                        this.$axios({
                            url:this.$url + "/contract/addContract",
                            method: 'post',
                            data: {
                                name: this.draftForm.name,
                                userName: this.draftForm.userName,
                                dataFrom: this.draftForm.dataFrom,
                                dataTo: this.draftForm.dataTo,
                                info: this.draftForm.info
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

    /*.box-card {*/
    /*    width: 50%;*/
    /*    left: 20%;*/
    /*    top: 15%;*/
    /*    position: relative;*/
    /*}*/


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

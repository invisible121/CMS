<template>
    <div>
        <el-table
                :data="tableData.filter(data => !search ||
                data.contract_name.toLowerCase().includes(search.toLowerCase()))"
                style="width: 100%">
            <el-table-column
                    label="序号"
                    prop="index">
            </el-table-column>

            <el-table-column
                    label="合同名称"
                    prop="contract_name">
            </el-table-column>
            <el-table-column
                    label="起草时间"
                    prop="date">
            </el-table-column>
            <el-table-column
                    align="right">
                <template slot="header" slot-scope="
/* eslint-disable vue/no-unused-vars */
scope">
                    <el-input
                            v-model="search"
                            size="mini"
                            placeholder="输入关键字搜索"/>
                </template>
                <template slot-scope="scope">
                    <!--                    <el-button-->
                    <!--                            size="mini"-->
                    <!--                            @click="handleEdit(scope.$index, scope.row)">修改-->
                    <!--                    </el-button>-->

                    <el-button
                            size="mini"
                            @click="handleWatch(scope.$index, scope.row)">查看
                    </el-button>
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">审批
                    </el-button>
                    <!--                    <el-button-->
                    <!--                            size="mini"-->
                    <!--                            type="danger"-->
                    <!--                    >删除-->
                    <!--                    </el-button>-->
                    <!--                            @click="handleDelete(scope.$index, scope.row)"-->
                </template>
            </el-table-column>
        </el-table>


        <el-dialog title="修改合同" :visible.sync="dialogVisible">
            <edit-contract></edit-contract>
        </el-dialog>

        <el-dialog title="确认审批" :visible.sync="dialog2Visible">

            <!--            <el-form >-->
            <!--                <el-form-item label="定稿意见">-->
            <!--                    <el-input type="textarea" :rows="3" v-model="msg"></el-input>-->
            <!--                </el-form-item>-->
            <!--            </el-form>-->
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialog2Visible = false">取 消</el-button>
                <el-button type="primary" @click="postMsg">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="查看" :visible.sync="dialog3Visible">

            <el-form ref="draftForm" :model="draftForm" label-position="left" label-width="85px">
                <el-form-item label="合同名称:" prop="name">
                    <el-input v-model="draftForm.name"></el-input>
                </el-form-item>
                <el-form-item label="客户:" prop="userName">
                    <el-input v-model="draftForm.userName"></el-input>
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

            </el-form>
            <div slot="footer" class="dialog-footer">

                <el-button type="primary" @click="dialog3Visible = false">确 定</el-button>
            </div>
        </el-dialog>
        <!--        <el-pagination-->
        <!--                background-->
        <!--                layout="prev, pager, next"-->
        <!--                :total="1000"-->
        <!--                align="center">-->
        <!--        </el-pagination>-->
    </div>
</template>

<script>
    export default {
        name: "watchContract",

        data() {
            const generateData = () => {
                const data = [];
                this.$axios({
                    url: this.$url + "/contract/selectContractByType",
                    method: 'post',
                    data: {
                        // token: this.$store.state.token,
                        token: this.$store.state.token,
                        type: 'watchContract'
                        // page:this.$store.state
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

                    res.data.data.forEach((item, index) => {
                        data.push({
                            index: index + 1,
                            date: item.beginTime,
                            contract_name: item.name,
                            id: item.id
                        })
                    })
                });

                return data;
            };
            return {
                search: '',
                tableData: generateData(),
                dialogVisible: false,
                dialog2Visible: false,
                dialog3Visible: false,
                msg: '',
                index: -1,
                row: -1,
                draftForm: {
                    name: '',
                    userName: '',
                    date: '',
                    info: '',
                },
            }
        },
        methods: {
            handleEdit(index, row) {
                this.dialog2Visible = true;
                this.index = index;
                this.row = row;
                // eslint-disable-next-line no-console
                console.log(index, row);


            },

            handleWatch(index, row) {
                this.index = index;
                this.row = row;


                this.$axios({
                    url:this.$url + "/contract/selContract",
                    method: 'post',
                    data: {
                        token: this.$store.state.token,
                        id: this.row.id,
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
                    this.handleData(res);
                });
                this.dialog3Visible = true;
            },
            handleData(res) {
                if (res.data.state === -1) {
                    this.$notify({
                        title: '失败',
                        message: '获取失败！',
                        type: "error"
                    });

                    this.dialog3Visible = false;
                }

                if (res.data.state === 0) {
                    this.draftForm.name = res.data.data[0].name;
                    this.draftForm.userName = res.data.data[0].customer;
                    const date = [];
                    date[0] = res.data.data[0].beginTime;
                    date[1] = res.data.data[0].endTime;
                    this.draftForm.date = date;
                    this.draftForm.info = res.data.data[0].content;
                }

                // console.log("test",this.draftForm)
            },

            postMsg(){
                this.$axios({
                    url:this.$url + "/contract/watch",
                    method: 'post',
                    data: {
                        token: this.$store.state.token,
                        id: this.row.id,
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
                            message: '审批失败！',
                            type: "error"
                        })
                    }

                    if (res.data.state === 0) {

                        var data = [];
                        res.data.data.forEach((item, index) => {
                            data.push({
                                index: index + 1,
                                date: item.beginTime,
                                contract_name: item.name,
                                id: item.id
                            })
                        });

                        this.tableData = data;

                        this.$notify({
                            title: '成功',
                            message: '审批成功！',
                            type: "success"
                        })
                    }

                    this.msg = '';
                    this.dialog2Visible = false;
                })
            },

            handleDelete(index, row) {
                // eslint-disable-next-line no-console
                console.log(index, row);
            },

            comment() {
                // this.$router.push('/CommentBox');
                this.$prompt('定稿意见', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消'
                }).then(({value}) => {
                    this.$message({
                        type: 'success',
                        message: '提交成功！定稿意见为：' + value
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
                // this.$alert('<strong>这是 <i>HTML</i> 片段</strong>', 'HTML1 片段', {
                //     dangerouslyUseHTMLString: true
                // });
            }

        },
    }
</script>

<style scoped>

</style>

<template>
    <div>
        <el-table
                :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                style="width: 100%">
            <el-table-column
                    label="序号"
                    prop="index"
            >
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
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                :total="1000"
                align="center">
        </el-pagination>
    </div>
</template>

<script>
    /* eslint-disable no-console */

    export default {
        name: 'CommittedContract',
        data() {
            const generateData = () => {
                const data = [];
                this.$axios({
                    url: this.$url + "/contract/selectCommittedContract",
                    method: 'post',
                    data: {
                        token: this.$store.state.token,
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
                    this.loadData(res);
                    res.data.manager_entities.forEach((item, index) => {
                        data.push({
                            label: item.name,
                            key: index,
                            // pinyin: pinyin[index]
                        });
                        if (item.rol_id === 1) {
                            this.value.push(index);
                        }
                    })
                });

                return data;
            };
            return {
                tableData: [{
                    index:'1',
                    date: '2016-05-02',
                    contract_name: '王小虎',
                }, {
                    index:'2',
                    date: '2016-05-02',
                    contract_name: '王小虎',
                }, {
                    index:'3',
                    date: '2016-05-02',
                    contract_name: '王小虎',
                }, {
                    index:'4',
                    date: '2016-05-02',
                    contract_name: '王小虎',
                }],
                search: '',
                data: generateData(),
            }
        },
        methods: {
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            }
        },
    }
</script>

<style scoped>

</style>
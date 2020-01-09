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
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">分配
                    </el-button>
                </template>
            </el-table-column>
        </el-table>


        <el-dialog @close="closeDlg" title="分配合同" :visible.sync="dialog2Visible">
            <el-container direction="vertical">
                <el-container>
                    <el-header>分配会签人</el-header>
                    <el-main>
                        <el-transfer
                                filterable
                                filter-placeholder="请输入用户名"
                                v-model="commitValue"
                                :data="permissionData"
                                :titles="['待分配人员', '已分配人员列表']"
                                class="main-transfer"
                        @change="changeCommit">

                        </el-transfer>
                    </el-main>
                </el-container>

                <el-container>
                    <el-header>分配审批人</el-header>
                    <el-main>
                        <el-transfer
                                filterable
                                filter-placeholder="请输入用户名"
                                v-model="watchValue"
                                :data="permissionData"
                                :titles="['待分配人员', '已分配人员列表']"
                                class="main-transfer"
                        @change="changeWatch">

                        </el-transfer>
                    </el-main>
                </el-container>

                <el-container>
                    <el-header>分配签订人</el-header>
                    <el-main>
                        <el-transfer
                                filterable
                                filter-placeholder="请输入用户名"
                                v-model="signValue"
                                :data="permissionData"
                                :titles="['待分配人员', '已分配人员列表']"
                                class="main-transfer"
                        @change="changeSign">

                        </el-transfer>
                    </el-main>
                </el-container>
            </el-container>
        </el-dialog>
    </div>
</template>

<script>

    export default {
        name: "allocContract",

        data() {
            const generateData = () => {

                const data = [];

                this.$axios({
                    url: this.$url + "/contract/selectAllContract",/*ByProcess*/
                    method: 'post',
                    data: {
                        token: this.$store.state.token,
                        process: 1
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
                })

                return data;
            };


            return {
                search: '',
                tableData: generateData(),
                dialogVisible: false,
                dialog2Visible: false,
                msg: '',
                index: -1,
                row: -1,

                resData: [],
                commitValue: [],
                watchValue: [],
                signValue: [],
                permissionData: []

            }
        },


        methods: {

            closeDlg() {
                this.permissionData = [];
                this.commitValue = [];
                this.watchValue = [];
                this.signValue = [];

                // eslint-disable-next-line no-console
                console.log(this.permissionData);
            },

            handleEdit(index, row) {
                this.index = index;
                this.row = row;

                this.dialog2Visible = true;

                const generatePermissionData = () => {

                    const perData = [];
                    this.$axios({
                        url: this.$url + "/contract/selectUserPermission",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
                            con_id: this.row.id
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

                        // eslint-disable-next-line no-unused-vars
                        res.data.users.forEach((item, index) => {
                            perData.push({
                                label: item.name,
                                key: item.id,
                            });

                            if (item.permissions === 1 ||
                                item.permissions === 3 ||
                                item.permissions === 5 ||
                                item.permissions === 7) {
                                this.commitValue.push(item.id)
                            }

                            if (item.permissions === 2 ||
                                item.permissions === 3 ||
                                item.permissions === 6 ||
                                item.permissions === 7) {
                                this.watchValue.push(item.id)
                            }

                            if (item.permissions === 4 ||
                                item.permissions === 5 ||
                                item.permissions === 6 ||
                                item.permissions === 7) {
                                this.signValue.push(item.id)
                            }
                        });



                    });
                    // eslint-disable-next-line no-console
                    console.log("0", this.commitValue);
                    // eslint-disable-next-line no-console
                    console.log("1", this.watchValue);
                    // eslint-disable-next-line no-console
                    console.log("2", this.signValue);
                    return perData;
                };

                this.permissionData = generatePermissionData();

            },

            changeCommit(value, direction, movedKeys) {
                if (direction === "right") {
                    this.$axios({
                        url: this.$url + "/contract/updatePermission",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
                            con_id: this.row.id,
                            type: 1,
                            list: movedKeys
                        },
                        transformRequest: [function (data) {
                            let ret = '';
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    })
                }
                if (direction === "left") {
                    this.$axios({
                        url: this.$url + "/contract/updatePermission",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
                            con_id: this.row.id,
                            type: -1,
                            list: movedKeys
                        },
                        transformRequest: [function (data) {
                            let ret = '';
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    })
                }
            },

            changeWatch(value, direction, movedKeys) {
                if (direction === "right") {
                    this.$axios({
                        url: this.$url + "/contract/updatePermission",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
                            con_id: this.row.id,
                            type: 2,
                            list: movedKeys
                        },
                        transformRequest: [function (data) {
                            let ret = '';
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    })
                }
                if (direction === "left") {
                    this.$axios({
                        url: this.$url + "/contract/updatePermission",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
                            con_id: this.row.id,
                            type: -2,
                            list: movedKeys
                        },
                        transformRequest: [function (data) {
                            let ret = '';
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    })
                }
            },

            changeSign(value, direction, movedKeys) {
                if (direction === "right") {
                    this.$axios({
                        url: this.$url + "/contract/updatePermission",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
                            con_id: this.row.id,
                            type: 4,
                            list: movedKeys
                        },
                        transformRequest: [function (data) {
                            let ret = '';
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    })
                }
                if (direction === "left") {
                    this.$axios({
                        url: this.$url + "/contract/updatePermission",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
                            con_id: this.row.id,
                            type: -4,
                            list: movedKeys
                        },
                        transformRequest: [function (data) {
                            let ret = '';
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    })
                }
            }
        }

    }
</script>

<style scoped>

</style>

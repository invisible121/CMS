<template>

    <el-container direction="vertical">
        <el-container >
            <el-header>分配会签人</el-header>
            <el-main>
                <el-transfer
                        filterable
                        filter-placeholder="请输入用户名"
                        v-model="commitValue"
                        :data="data"
                        :titles="['待分配人员', '已分配人员列表']"
                        class="main-transfer"
                        @change="handleChange">

                </el-transfer>
            </el-main>
        </el-container>

        <el-container >
            <el-header>分配审批人</el-header>
            <el-main>
                <el-transfer
                        filterable
                        filter-placeholder="请输入用户名"
                        v-model="watchValue"
                        :data="data"
                        :titles="['待分配人员', '已分配人员列表']"
                        class="main-transfer"
                        @change="handleChange">

                </el-transfer>
            </el-main>
        </el-container>

        <el-container >
            <el-header>分配签订人</el-header>
            <el-main>
                <el-transfer
                        filterable
                        filter-placeholder="请输入用户名"
                        v-model="SignValue"
                        :data="data"
                        :titles="['待分配人员', '已分配人员列表']"
                        class="main-transfer"
                        @change="handleChange">

                </el-transfer>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "permission",
        data() {
            const generateData = () => {
                this.$axios({
                    url: this.$url + "/contract/selectUserPermission",
                    method: 'post',
                    data: {
                        token: this.$store.state.token,
                        con_id: 1
                    },
                    transformRequest: [function (data) {
                        let ret = '';
                        for (let it in data) {
                            ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                        }
                        return ret
                    }],
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).than(res => {
                    // eslint-disable-next-line no-console
                    console.log(res)
                })

            };


            return {
                resData: [],
                commitValue: [],
                watchValue: [],
                signValue: [],
                data: generateData(),
            }
        },
        methods: {}
    }
</script>

<style scoped>

</style>

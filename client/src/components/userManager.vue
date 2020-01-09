<template>
    <el-container direction="horizontal" style=" height: 100%;width: 100%; top: 0; bottom: 0;">
        <el-aside v-show="false" width="20%" style="height: 100%">
            <el-menu
                    class="el-menu-vertical"
                    style="height: 100%"
                    :collapse="isFold">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>导航一</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title">分组一</template>
                        <el-menu-item index="1-1">选项1</el-menu-item>
                        <el-menu-item index="1-2">选项2</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="分组2">
                        <el-menu-item index="1-3">选项3</el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="1-4">
                        <template slot="title">选项4</template>
                        <el-menu-item index="1-4-1">选项1</el-menu-item>
                    </el-submenu>
                </el-submenu>
                <el-menu-item index="2">
                    <i class="el-icon-menu"></i>
                    <span slot="title">导航二</span>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                    <i class="el-icon-document"></i>
                    <span slot="title">导航三</span>
                </el-menu-item>
                <el-menu-item index="4">
                    <i class="el-icon-setting"></i>
                    <span slot="title">导航四</span>
                </el-menu-item>
            </el-menu>
        </el-aside>

        <el-main>
            <el-transfer
                    filterable
                    filter-placeholder="请输入用户名"
                    v-model="value"
                    :data="data"
                    :titles="['用户', '管理员']"
                    class="main-transfer"
                    @change="handleChange">
                <el-button class="transfer-footer el-icon-minus" slot="left-footer" size="small" circle></el-button>
                <el-button class="transfer-footer el-icon-plus" slot="left-footer" size="small" circle></el-button>

                <el-button class="transfer-footer el-icon-minus" slot="right-footer" size="small"
                           circle></el-button>
                <el-button class="transfer-footer el-icon-plus" slot="right-footer" size="small" circle></el-button>

            </el-transfer>


        </el-main>

    </el-container>

</template>

<script>
    export default {
        name: "userManager",
        data() {
            const generateData = () => {
                const data = [];
                this.$axios({
                    url: this.$url + "/user/selectAllRight",
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
                fold: 'el-icon-s-unfold',
                unfold: 'el-icon-s-fold',
                myIcon: 'el-icon-s-fold',
                isFold: false,
                token: "",
                resData: [],
                value: [],
                data: generateData(),

            }
        },
        mounted: function () {
            this.load()
        },
        methods: {
            load() {
                if (this.$store.state.token === "") {
                    this.$alert('请登录账号', '警告', {
                        confirmButtonText: '确定',
                        callback: {}
                    });
                    this.$router.push("/login");
                }
                this.token = this.$store.state.token
            },

            loadData(res) {
                if (res.data.state === 0) {
                    this.resData = res.data.manager_entities;
                    // eslint-disable-next-line no-console
                    // console.log(this.resData)
                }

                if (res.data.state === -1) {
                    // alert("请登录！")
                }
                if (res.data.state === 1) {
                    // alert("无权限！")
                }
            },

            handleChange(value, direction, movedKeys) {
                // eslint-disable-next-line no-console
                console.log(movedKeys);
                if (direction === "right") {
                    this.$axios({
                        url: this.$url + "/user/addRootList",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
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
                        url: this.$url + "/user/addUserList",
                        method: 'post',
                        data: {
                            token: this.$store.state.token,
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


    .main-submenu {
        float: right;
    }

    .main-submenu:focus {
        color: white;
    }

    .el-menu-mainBar {
        margin-left: 0;
        margin-top: 0;
    }

    .el-menu-mainBar .is-active {
        color: #ffffff;
        background-color: rgb(0, 98, 204);
    }


    .main-transfer {
        margin: auto;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: fit-content;
        position: absolute;
        background-color: white;
        /*padding: 20px 20px 10px 20px;*/
        border-radius: 10px;
        box-shadow: 0px 15px 25px 0px rgba(0, 0, 0, 0.11);
        padding: 15px;

    }
</style>

<style>
    /*.el-submenu__title i {*/
    /*    color: #ffffff;*/
    /*}*/

    .tac {
        margin: 0;
        height: 100%;
    }

    html, body {
        margin: 0;
        top: 0;
        bottom: 0;
        height: 100%;
    }

    .el-menu-mainBar i {
        color: #ffffff;
    }

    .el-menu-mainBar .is-active {
        color: #ffffff;
        background-color: rgb(0, 98, 204);
    }

    .el-menu--horizontal .el-menu-item:not(.is-disabled):focus {
        color: #ffffff;
    }


</style>

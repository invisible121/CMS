<template>
    <el-container style="width: 100%;height: 100%; top: 0; bottom: 0;">
        <el-header style="padding: 0">

            <el-menu active-text-color="#ffffff" default-active="/userManager" :router="true" class="el-menu-mainBar"
                     mode="horizontal"
                     background-color="#007BFF" text-color="#ffffff">
                <el-menu-item v-show="false">
                    <el-button class="foldButton" :icon="myIcon" type="text"
                               style="background-color: rgba(0, 0, 0, 0);
                               border: 0px; height: 100%; width: 100%;
                                padding-top: auto; padding-bottom: auto;"
                               @click="clickFold"></el-button>
                </el-menu-item>
                <el-menu-item class="main-submenu" index="userManager">
                    <template slot="title">用户管理</template>
                </el-menu-item>
                <el-menu-item class="main-submenu" index="permission">
                    <template slot="title">分配合同</template>
                </el-menu-item>
            </el-menu>
        </el-header>


        <router-view></router-view>
    </el-container>
</template>

<script>
    export default {
        name: "manager",
        data() {

            return {
                fold: 'el-icon-s-unfold',
                unfold: 'el-icon-s-fold',
                myIcon: 'el-icon-s-fold',
                isFold: false,
                token: "",
                resData: [],
                value: [],
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
            clickFold() {
                this.isFold = !this.isFold;
                this.myIcon = this.myIcon === this.unfold ? this.fold : this.unfold;

            },


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

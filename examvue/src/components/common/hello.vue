<template>
  <section class="index">
    <div class="hello">
      <i class=""></i><span>你好,{{user.userName}}</span>
    </div>
    <div class="msg">
      <p class="title">系统公告：</p>
      <p>&nbsp;&nbsp;&nbsp;1234</p>
      <ul>
    
        <li @click="openMsg1()"><i ></i>测试1</li>
        <li @click="openMsg1()"><i ></i>测试2</li>
       
      </ul>
    </div>
  </section>
</template>

<script>
import { ninvoke } from 'q'

export default {
  data() {
    return {
      user: { //用户信息
        userName: null,
        userId: null,
        usersex:null,
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() { //获取用户信息
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      let usersex = this.$cookies.get("csex")
      this.user.userName = userName
      this.user.userId = userId
      this.user.usersex = usersex
    },
    // openMsg() {
    //   this.$alert('留言功能已开通，请管理员认真查看用户对本系统的留言','留言功能已开通',{
    //     confirmButtonText: '确定'
    //   })
    // },
    // openMsg2() {
    //   this.$alert('试题难度下降','试题难度下降',{
    //     confirmButtonText: '确定'
    //   })
    // },
    openMsg1() {
      this.$alert('测试','测试',{
        confirmButtonText: '确定'
      })
    },
  }
}
</script>


<style lang="scss" scoped>
.index {
  margin-left: 70px;
  .hello {
    font-size: 20px;
    color: #726f70;
    .icon-xihuan {
      font-size: 30px;
      color: #dd6572;
    }
  }
  .msg {
    .title {
      font-size: 16px;
      color: #000;
      margin-top: 20px;
      margin-left: 10px;
    }
    ul {
      display: flex;
      flex-direction: column;
      width: 200px;
      overflow: hidden;
    }
    li {
      margin-top: 10px;
      font-size: 14px;
      color: lightcoral;
      cursor: pointer;
      display: inline-block;
    }
  }
}
</style>


<template>
  <div class="score">
    <div class="title">
      <p class="name">成绩单</p>
      <p class="description">(总分：100分,限时：100分钟)</p>
    </div>
    <div class="total">
      <div class="look">
        本次考试成绩
      </div>
      <div class="show">
        <div class="img1" :class="{'img1Transform': imgShow}">
          <img :src="imgSrc.fail1" alt="哭了" v-if="score < 60">
          <img :src="imgSrc.pass1" alt="过了" v-if="score >= 60">
        </div>
        <div class="number" :class="{'border': isTransition}">
          <span>{{score}}</span>
          <span>分数</span>
        </div>
        <div class="img2" :class="{'img2Transform': imgShow}">
          <img :src="imgSrc.fail2" alt="哭了" v-if="score < 60">
          <img :src="imgSrc.pass2" alt="过了" v-if="score >= 60">
        </div>
      </div>
      <ul class="time">
        <li class="start"><span>开始时间</span> <span>{{startTime}}</span></li>
        <li class="end"><span>结束时间</span> <span>{{endTime}}</span></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isTransition: false, //是否渲染完成
      score: 0, //总分
      imgShow: false, //不及格图片显示
      imgSrc: {
        fail1: require("@/assets/img/伤心sad.jpg"),
        fail2: require('@/assets/img/伤心sad.jpg'),
        pass1: require('@/assets/img/good1.jpg'),
        pass2: require('@/assets/img/good2.gif')
      },
      startTime: null, //考试开始时间
      endTime: null, //考试结束时间
    }
  },
  created() {
    this.imgShow = true
    this.transiton()
    this.getScore()
  },
  methods: {
    transiton() { 
      setTimeout(() => {
        this.isTransition = true
        
      },1000)
    },
    getScore() {
      let score = this.$route.query.score
      let startTime = this.$route.query.startTime
      let endTime = this.$route.query.endTime
      this.score = score
      this.startTime = startTime
      this.endTime = endTime
    }
  }
}
</script>

<style lang="scss" scoped>
.show {
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 160px;
    height: 160px;
  }
  .img1Transform {
    opacity: 1 !important;
    transform: translateX(30px) !important;
    transition: all 0.6s ease !important;
  }
  .img2Transform {
    opacity: 1 !important;
    transform: translateX(-30px) !important;
    transition: all 0.6s ease !important;
  }
  .img1 {
    margin-top: 50px;
    opacity: 0;
    transform: translateX(0px);
    transition: all 0.6s ease;
  }
  .img2 {
    margin-top: 30px;
    opacity: 0;
    transform: translateX(0px);
    transition: all 0.6s ease;
  }
}
.time {
  padding: 0px 70px;
  li {
    display: flex;
    justify-content: space-around;
    padding: 10px;
    margin: 20px 0px;
  }
  li:nth-child(1) {
    background-color: #f5d328;
  }
  li:nth-child(2) {
    background-color: #f5d328;
  }
}
.border {
  border: 6px solid #36aafd !important;
  transition: all 2s ease;
  width: 160px !important;
  height: 160px !important;
  transform: rotate(360deg) !important;
  opacity: 1 !important;
}
.score {
  max-width: 800px;
  margin: 0 auto;
  .title {
    margin: 60px 0px 30px 0px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    .name {
      font-size: 26px;
      color: inherit;
      font-weight: 500;
    }
    .description {
      font-size: 14px;
      color: #888;
    }
  }
  .total {
    border: 1px solid #dbdbdb;
    background-color: #fff;
    padding: 40px;
    .look {
      border-bottom: 1px solid #dbdbdb;
      padding: 0px 0px 14px 14px;
      color: #101011;
    }
    .number {
      opacity: 0;
      border: 6px solid #fff;
      transform: rotate(360deg);//旋转角度
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      margin: 0 auto;//图片边距
      width: 160px;
      height: 160px;
      border-radius: 50%;
      margin-top: 80px;
      margin-bottom: 20px;
      transition: all 1s ease;

      span:nth-child(1) {
        font-size: 36px;//数字大小
        font-weight: 600;
      }
      span:nth-child(2) {
        font-size: 14px;//文本大小
      }
    }
  }
}
</style>


<template>
   <el-card class = "box-card" shadow = "never">
        <div slot  = "header">
            <span>🍻推广</span>
        </div>
        <div>
            <p v-for="(item,index) in list " :key="index" class="block">
              <a :href="item.link" target="_blank">{{ item.title }}</a>
            </p>
        </div>
    </el-card> 
</template>


<script>

import { getPromotion } from '@/api/promotion'

export default {
  name: 'Promorion',
  data() {
    return {
      list: []
    }
  },
  created() {
    this.fetchPromotion()  
  },
  methods : {
    fetchPromotion() {
      getPromotion().then(response => {
          const { data } = response
          this.list = data
        }
      )
    }
    
  }
  
}
</script>

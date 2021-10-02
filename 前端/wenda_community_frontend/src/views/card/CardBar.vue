<template>
  <div >
  
    <LoginWelcome></LoginWelcome>
    <Tip></Tip> 
    <Promotion></Promotion>
    
  </div>

</template>



<script>
import LoginWelcome from  '@/views/card/LoginWelcome'
import Promotion from  '@/views/card/Promotion'
import Tip from  '@/views/card/Tip'


export default {
  name: 'TopicList',
  components : {LoginWelcome,Promotion , Tip},
  data() {
    return {
      
    }
  },
  created() {
    
  },
  methods : {
    
  }
  
}
</script>

<template>
  <section>
    <div class="columns">
      <div class="column is-full">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span><i class="fa fa fa-book"> 主题 / 更新主题</i></span>
          </div>
          <div>
            <el-form :model="newdata.topic" ref="topic" class="demo-topic">
              <el-form-item prop="title">
                <el-input
                  v-model="newdata.topic.title"
                  placeholder="输入新的主题名称"
                ></el-input>
              </el-form-item>

              <!--Markdown-->
              <div id="vditor"></div>

              <b-taginput
                v-model="newdata.tags"
                class="my-3"
                maxlength="15"
                maxtags="3"
                ellipsis
                placeholder="请输入主题标签，限制为 15 个字符和 3 个标签"
              />
              <el-form-item class="mt-3">
                <el-button type="primary" @click="handleUpdate()"
                  >更新
                </el-button>
                <el-button @click="resetForm('topic')">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </div>
    </div>
  </section>
</template>

<script>
import { getTopic, update } from "@/api/post";
import Vditor from "vditor";
import "vditor/dist/index.css";

export default {
  name: "TopicEdit",
  components: {},
  data() {
    return {
      newdata: {
        topic:{},
        tags: [] // 标签
      }        
    };
  },
  created() {
    this.fetchTopic();
  },
  methods: {
    renderMarkdown(md) {
        console.log(md)
      this.contentEditor = new Vditor("vditor", {
        height: 460,
        placeholder: "输入要更新的内容",
        preview: {
          hljs: { style: "monokai" },
        },
        mode: "sv",
        after: () => {
          this.contentEditor.setValue(md);
          
        },
      });

    },
    fetchTopic() {
      getTopic(this.$route.params.id).then((value) => {
        console.log(value)
        this.newdata.topic = value.data.topic;
        this.newdata.tags = value.data.tags.map(tag => tag.name);
        this.renderMarkdown(this.newdata.topic.content);
        console.log("this.newdata")
        console.log(this.newdata)
      });
    },
    handleUpdate: function () {
      this.newdata.topic.content = this.contentEditor.getValue();
      console.log(this.newdata)
      update(this.newdata).then((response) => {
        const { data } = response;
        console.log(data);
        setTimeout(() => {
          this.$router.push({
            name: "post-detail",
            params: { id: data.id },
          });
        }, 800);
      });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.contentEditor.setValue("");
      this.newdata.tags = [];
    },
  },
};
</script>

<style>
.vditor-reset pre > code {
  font-size: 100%;
}
</style>

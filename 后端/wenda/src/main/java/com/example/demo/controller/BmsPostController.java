package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.api.ApiResult;
import com.example.demo.model.dto.CreateTopicDTO;
import com.example.demo.model.dto.UpDataPoat;
import com.example.demo.model.entity.*;
import com.example.demo.model.vo.PostVO;
import com.example.demo.service.*;
import com.example.demo.utils.MD5Utils;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.*;

import static com.example.demo.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Resource
    private IBmsPostService iBmsPostService;
    @Resource
    private IUmsUserService umsUserService;

    @Resource
    private IBmsCommentService iBmsCommentService;

    @Resource
    private IBmsTopicTagService iBmsTopicTagService;

    @Resource
    private IBmsTagService iBmsTagService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<BmsPost> create(@RequestHeader(value = USER_NAME) String userName
            , @RequestBody CreateTopicDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost topic = iBmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }
    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewTopic(id);
        System.out.println("这是一个map");
        System.out.println(map);
        return ApiResult.success(map);
    }

    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> getRecommend(@RequestParam("topicId") String id) {
        List<BmsPost> topics = iBmsPostService.getRecommend(id);
        return ApiResult.success(topics);
    }

    @PostMapping("/update")
    public ApiResult<BmsPost> update(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody Map<String,Object> post) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);

       /* UmsUser addUser = UmsUser.builder().username(dto.getName()).alias(dto.getName())
                .password(MD5Utils.getPwd(dto.getPass())).email(dto.getEmail())
                .createTime(new Date()).status(true).build();

        Assert.isTrue(umsUser.getId().equals(post.get("topic").getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        iBmsPostService.updateById(post);
        iBmsTopicTagService.remove((new LambdaQueryWrapper<BmsTopicTag>().eq(BmsTopicTag::getTopicId,post.getId())));
*/
        System.out.println(post.get("topic"));
        List<String> topicListcopy = Arrays.asList(post.get("topic").toString().substring(1).split(",").clone());
        List<String> topicList = new ArrayList<>();
        for(int i = 0 ;i<topicListcopy.size();i++){
            System.out.println(topicListcopy.get(i).split("=")[1]);
            topicList.add(topicListcopy.get(i).split("=")[1]);
        }
        BmsPost bmsPost = BmsPost.builder().id(topicList.get(0)).title(topicList.get(1)).content(topicList.get(2))
                .userId(topicList.get(3)).comments(Integer.valueOf(topicList.get(4))).collects(Integer.valueOf(topicList.get(5)))
                .view(Integer.valueOf(topicList.get(6))).top(Boolean.valueOf(topicList.get(9))).essence(Boolean.valueOf(topicList.get(8)))
                .sectionId(Integer.valueOf(topicList.get(7))).modifyTime(new Date()).build();
        iBmsPostService.updateById(bmsPost);
        /*// 标签
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> tags = iBmsTagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            IBmsTopicTagService.createTopicTag(topic.getId(), tags);
        }*/
        iBmsTopicTagService.remove(new LambdaQueryWrapper<BmsTopicTag>().eq(BmsTopicTag::getTagId,bmsPost.getId()));
        if (!ObjectUtils.isEmpty(post.get("tags"))) {
            String tagStr = post.get("tags").toString();
            tagStr = tagStr.substring(1,tagStr.length()-1);
            List<String> list = Arrays.asList(tagStr.split(","));
            List<String> listTag = new ArrayList<>();
            for(String we : list){
                listTag.add(we.trim().replace("#",""));
            }
            // 保存标签
            List<BmsTag> tags = iBmsTagService.insertTags(listTag);
            // 处理标签与话题的关联
            iBmsTopicTagService.createTopicTag(bmsPost.getId(), tags);
        }
        return ApiResult.success(bmsPost);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@RequestHeader(value = USER_NAME) String userName, @PathVariable("id") String id) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        BmsPost byId = iBmsPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(umsUser.getId()), "你为什么可以删除别人的话题？？？");
        iBmsPostService.removeById(id);
        iBmsCommentService.remove((new LambdaQueryWrapper<BmsComment>().eq(BmsComment::getTopicId,id)));

        return ApiResult.success(null,"删除成功");
    }
}

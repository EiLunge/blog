package com.sx.blog.sys.service.Impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.QueryArticleParam;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.vo.TitleArticleVO;
import com.sx.blog.sys.mapper.CommentMapper;
import com.sx.blog.sys.mapper.ShowMapper;
import com.sx.blog.sys.service.IShowService;


@Service
public class ShowServiceImpl implements IShowService{

	@Autowired
	private ShowMapper showMapper; 
    @Autowired
    private CommentMapper commentMapper;


	//根据标签id查询标签名
	@Override
	public List<List<String>> getTagNameByTagId(List<Article> list) {
		//声明一个集合用于存放标签
		List<List<String>> tagList = new ArrayList<>();
		for (Article article : list) {
			//处理标签
			List<String> data = new ArrayList<>();
			String str =  article.getTagId();
			String[] result = str.split(",");		
			for(String tag:result){
				//标签名替换
				String tagName = showMapper.findTagNameByTagId(Integer.parseInt(tag));
				data.add(tagName);
			}

			tagList.add(data);
		}		
		return tagList;
	}

	//根据用户id查询用户名
	public List<String> getUserNameByUserId(List<Article> list) {
		//声明一个集合用于存储用户名
		List<String> nameList = new ArrayList<>();
		for (Article article : list) {
			//处理用户名
			Integer user_id = article.getUserId();
			//获取用户名并添加到集合
			String name = showMapper.findUserNameByUserId(user_id);
			nameList.add(name);
		}				
		return nameList;
	}

    // 查询评论最多的4篇文章的标题
	@Override
    public List<TitleArticleVO> getTitleByCommentNum() {
        // 查询出最近七天的文章
        List<Article> articleList = showMapper.findArticlesByTime();
        for (int i = 0; i < articleList.size(); i++) {
            int commentNum = commentMapper.getCommentsNumByArticleId(articleList.get(i).getArticleId());
            articleList.get(i).setCommentNum(commentNum);
        }
        // 按评论量排序
        List<Article> sortUser = articleList.stream()
            .sorted((u1, u2) -> u2.getCommentNum().compareTo(u1.getCommentNum())).collect(Collectors.toList());
        // 拼装，且只显示4篇
        List<TitleArticleVO> title = new LinkedList<TitleArticleVO>();
        for (int i = 0; i < sortUser.size(); i++) {
            title.add(new TitleArticleVO(sortUser.get(i).getTitle(), sortUser.get(i).getArticleId()));
        }
        return title;
	}

    // 查询游览量最多的4篇文章标题（15天内）
	@Override
	public List<TitleArticleVO> getTitleByBrowse() {
		return showMapper.findTitleByBrowse();
	}

	//通过用户id获取关注人博文
	@Override
	public List<Article> getFollowArticleByUserId(Integer userId) {
		return showMapper.findFollowArticleByUserId(userId);

	}

	
	//从标签表中查询所有的标签名
	@Override
    public List<Tag> getAllTagsWithTag() {
		return showMapper.findAllTagsWithTag();	
	}

	
	//根据文章集合查询用户头像的集合
	@Override
	public List<String> getIconByArticleId(List<Article> list) {
		//声明一个集合用于存储头像
		List<String> iconList = new ArrayList<>();
		for (Article article : list) {
			Integer articleId = article.getArticleId();
			String icon = showMapper.findIconByArticleId(articleId);
			iconList.add(icon);
		}
		return iconList;
	}
	
	//根据文章id查询用户名
	@Override
	public String getUsernameByArticleId(Integer articleId) {
		Integer userId = showMapper.findUserIdByArticleId(articleId);
		String username = showMapper.findUserNameByUserId(userId);
		return username;
	}

    // 查询被推荐文章
    @Override
    public List<Article> getRecommendArticle() {
        List recommendArticle = showMapper.findArticleByRecommend();
        return recommendArticle;
    }

    /* 文章查询
     */
    @Override
    public List<Article> getArtilesByParam(QueryArticleParam param) {
        List<Article> articleList = showMapper.getArtilesByParam(param);
        return articleList;
    }


}

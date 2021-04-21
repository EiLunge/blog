package com.sx.blog.sys.service.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.vo.UserVo;
import com.sx.blog.sys.mapper.ArticleMapper;
import com.sx.blog.sys.mapper.CommentMapper;
import com.sx.blog.sys.mapper.ShowMapper;
import com.sx.blog.sys.service.ArticleService;
import com.sx.blog.sys.service.UserService;
/**
 * 编辑博文
 * @author 邓宇阳
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper  articleMapper;
	@Autowired
	private ShowMapper showMapper;
	@Autowired
	private UserService userService;
    @Autowired
    private CommentMapper commentMapper;

	/**
	 * 打开编辑器页面,展现分类标签
	 */
	@Override
	public List<Tag> findTag() {
		List<Tag> tag = articleMapper.selectTagName();
		return tag;
	}
	/**
	 * 保存添加新文章  0 草搞  1 发布 2删除
	 */
	@Override
	public void addArticle(Article article) {
		articleMapper.addArticle(article);
	}
	/**
	 * 根据文章id查询文章信息
	 */
	@Override
	public Article getArticleById(Integer articleId) {
		Article article = articleMapper.getArticleById(articleId);
		Integer browse = article.getBrowse();
		browse = browse + 1;
		//增加浏览量
		articleMapper.addBrowse(browse,articleId);
		return article;
	}
	/**
	 * 根据文章id查询用户信息
	 * 1.用户基本信息
	 * 2.用户点赞数量
	 * 3.用户博文数量
	 * 4.用户粉丝数量
	 */
	@Override
	public UserVo getUserByArticleId(Integer articleId) {
		Article article = articleMapper.getArticleById(articleId);
		//查询用户基本信息
		User user = articleMapper.getUserById(article.getUserId());
		UserVo userVo=userService.getUserDetail(user.getUserId());
		return userVo;
	}

	/**
	 * 上传图片
	 */
	@Override
	public Map<String, Object> uploadfileImages(MultipartFile file, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//1.创建存放文件路径夹路径
			String localhostPath = "D:/GitWorkesp/blog/images/";
			//1.2获取文件名
			String trueFileName = file.getOriginalFilename();
			//1.3获取文件后缀名
			String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
			//1.4生成新文件的名字
			String fileName = System.currentTimeMillis()+suffix;
			//1.5创建文件名路径
			String urlImages = "/images/"+fileName;
			//2.创建文件上传保存本地
			File targetFile = new File(localhostPath,fileName);
			//3.判断是否存在当前文件夹路径,不存在就创建
			if(!targetFile.getParentFile().exists())
				targetFile.getParentFile().mkdirs();
			//4.上传
			file.transferTo(targetFile);
			resultMap.put("success", 1);
			resultMap.put("message", "上传成功！");
			resultMap.put("url",urlImages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	@Override
	public String getTagIdByTagName(List<String> tag) {
		//利用for循环遍历标签集合
		List<String> tagIdMain = new ArrayList<>();
		for (String tagName : tag) {
			//替换标签名为标签id
			Integer tagId = showMapper.findTagIdByTagName(tagName);
			tagIdMain.add(tagId.toString());
		}
		//把Integer类型的标签id转化为字符串类型
		String myTagId = String.join(",", tagIdMain);  
		//返回标签String类型的标签id
		return myTagId;
	}

    /**
     * 回显文章标签信息
     * 
     * @param articleId
     * @return tagId List
     */
    @Override
    public List<Integer> selectTagsIdByArticleId(Integer articleId) {
        String tagIds = articleMapper.selectTagsIdByArticleId(articleId);
        List<Integer> tagIdList = new LinkedList<Integer>();
        String[] tagId = tagIds.split("\\,");
        for (int i = 0; i < tagId.length; i++) {
            tagIdList.add(Integer.parseInt(tagId[i]));
        }
        return tagIdList;
    }

    /**
     * 删除文章
     * 
     * @param articleId
     */
    public void delArticle(Integer articleId) {
        commentMapper.delCommentsByArticleId(articleId);
        articleMapper.delArticle(articleId);
    }















}

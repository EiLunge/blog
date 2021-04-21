var index = 0;
//点击刷新
$("#next_article").click(function(){
	console.log("点击！！！")
	$.ajax({
		"url" : "/show/clickArticlesList",
		"data" : {index:index},
		"type" : "POST",    
		"dataType" : "json",
		success : function(json) {
			index++;
			let list = json.data;
			console.log(list)
			let articles;
			//let tagList;
			let nameList;
			for(let n = 0; n<list.length; n++){
				if(n == 0){
					articles = list[0];
				}else{
					nameList = list[1];
				}
			}
			if(articles.length == 0){
				$("#empty_note").empty();
				let html = '<span style="font-size:20px">没有查询到任何内容！！！！！</span>';
				$("#empty_note").append(html);
			}else{
				for (let i = 0; i < articles.length; i++) {
					let html = '<ul class="article-list">'
						+ '<li class="article">'
						+ '<div class="entry">'
						+ '<div class="content">'
						+ '<h2>'
						+ '<a href="#11" target="_blank" class="title">'
						+'<span class="recommend">'
						+ '<div class="recommend-text">荐</div>'
						+ '</span >#{title}'
						+ '</a>'
						+ '</h2>'
						+ '<div class="summary">#{abstracts}</div>'
						+ '<dl class="user-bar">'
						+ '<dt>'
						+ '<a href="######" target="_blank" class="user_img">'
						+ '<img src="/images/kuangsan-2.png" alt="silentwolfyh" title="silentwolfyh" username="silentwolfyh">'
						+ '</a>'
						+ '</dt>'
						+ '<dd class="user_name">#{username}</dd>'
						+ '<div class="interactive">'
						+ '<span class="click_heart">'
						+ '<a href="xxx">'
						+ '<span class="text">'
						+ '<i class="layui-icon layui-icon-praise"></i>'
						+ '</span>'
						+ '<span class="num">#{likes}</span>'
						+ '</a>'
						+ '</span>'
						+ '<span class="read_num">'
						+ '<a href="xxx">'
						+ '<span class="text">'
						+ '<text class="iconfont icon-youlan"></text>'
						+ '</span>'
						+ '<span class="num">#{browse}</span>'
						+ '</a>'
						+ '</span>'
						+ '<span class="comment_num">'
						+ '<a href="xxx">'
						+ '<span class="text">'
						+ '<text class="iconfont icon-huifu"></text>'
						+ '</span>'
						+ '<span class="num">#{commentNum}</span>'
						+ '</a>'
						+ '</span>'
						+ '</div>'
						+ '</dl>'
						+ '</div>'
						+ '</div>'
						+ '</li>'
						+ '</ul>'

						html = html.replace(/#{title}/g, articles[i].title);
					if(articles[i].abstracts == null){
						html = html.replace(/#{abstracts}/g, articles[i].pcContent);
					}else{
						html = html.replace(/#{abstracts}/g, articles[i].abstracts);
					} 
					//html = html.replace(/#{image}/g, articles[i].images);
					//html = html.replace(/#{createTime}/g, articles[i].createTime);
					html = html.replace(/#{likes}/g, articles[i].likes);
					html = html.replace(/#{browse}/g, articles[i].browse);
					html = html.replace(/#{commentNum}/g, articles[i].commentNum);
					html = html.replace(/#{username}/g, nameList[i]);	           
					$("#click_bottom").append(html);
				}
			}		    		

		}

	});	

});

//监听页面刷新
window.onbeforeunload = function(){
	console.log("页面刷新捕捉！")
	$("#click_bottom").empty();
	$("#empty_note").empty();
	index = 0;
}; 

//关注人博文
$("#follow_man").click(function(){
	$.ajax({
		"url" : "/show/followList",
		"type" : "POST",    
		"dataType" : "json",
		success : function(json) {
			let list = json.data;
			let articles;
			let nameList;
			for(let n = 0; n<list.length; n++){
				if(n == 0){
					articles = list[0];
				}else{
					nameList = list[1];
				}
			}
			console.log(articles)
			$("#all_article").empty();
			$("#control_enter").empty();
			if(articles.length == 0){		    		
				let html = '<span style="font-size:20px">没有查询到任何内容！！！！！</span>';
				$("#all_article").append(html);
			}else{	
				for (let i = 0; i < articles.length; i++) {
					let html = '<ul class="article-list">'
						+ '<li class="article">'
						+ '<div class="entry">'
						+ '<div class="content">'
						+ '<h2>'
						+ '<a href="#11" target="_blank" class="title">'
						+'<span class="recommend">'
						+ '<div class="recommend-text">荐</div>'
						+ '</span >#{title}'
						+ '</a>'
						+ '</h2>'
						+ '<div class="summary">#{abstracts}</div>'
						+ '<dl class="user-bar">'
						+ '<dt>'
						+ '<a href="######" target="_blank" class="user_img">'
						+ '<img src="/images/kuangsan-2.png" alt="silentwolfyh" title="silentwolfyh" username="silentwolfyh">'
						+ '</a>'
						+ '</dt>'
						+ '<dd class="user_name">#{username}</dd>'
						+ '<div class="interactive">'
						+ '<span class="click_heart">'
						+ '<a href="xxx">'
						+ '<span class="text">'
						+ '<i class="layui-icon layui-icon-praise"></i>'
						+ '</span>'
						+ '<span class="num">#{likes}</span>'
						+ '</a>'
						+ '</span>'
						+ '<span class="read_num">'
						+ '<a href="xxx">'
						+ '<span class="text">'
						+ '<text class="iconfont icon-youlan"></text>'
						+ '</span>'
						+ '<span class="num">#{browse}</span>'
						+ '</a>'
						+ '</span>'
						+ '<span class="comment_num">'
						+ '<a href="xxx">'
						+ '<span class="text">'
						+ '<text class="iconfont icon-huifu"></text>'
						+ '</span>'
						+ '<span class="num">#{commentNum}</span>'
						+ '</a>'
						+ '</span>'
						+ '</div>'
						+ '</dl>'
						+ '</div>'
						+ '</div>'
						+ '</li>'
						+ '</ul>'

						html = html.replace(/#{title}/g, articles[i].title);
					if(articles[i].abstracts == null){
						html = html.replace(/#{abstracts}/g, articles[i].pcContent);
					}else{
						html = html.replace(/#{abstracts}/g, articles[i].abstracts);
					} 
					//html = html.replace(/#{image}/g, articles[i].images);
					//html = html.replace(/#{createTime}/g, articles[i].createTime);
					html = html.replace(/#{likes}/g, articles[i].likes);
					html = html.replace(/#{browse}/g, articles[i].browse);
					html = html.replace(/#{commentNum}/g, articles[i].commentNum);
					html = html.replace(/#{username}/g, nameList[i]);	           
					$("#all_article").append(html);
				}
			}		    	 

		} 
	});	      
})

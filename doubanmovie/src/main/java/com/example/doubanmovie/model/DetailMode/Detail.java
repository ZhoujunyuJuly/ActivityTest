package com.example.doubanmovie.model.DetailMode;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhoujunyu on 2019/3/21.
 */
public class Detail {

    /**
     * rating : {"max":10,"average":5,"details":{"1":695,"3":1145,"2":1089,"5":161,"4":365},"stars":"25","min":0}
     * reviews_count : 745
     * videos : []
     * wish_count : 18911
     * original_title : 比悲傷更悲傷的故事
     * blooper_urls : ["http://vt1.doubanio.com/201903200945/e560117381e1b1b46294593251df2447/view/movie/M/302440307.mp4","http://vt1.doubanio.com/201903200945/82170c38de26bd0e04e94d64647e6184/view/movie/M/302390582.mp4","http://vt1.doubanio.com/201903200945/c364836c80a7d596ceeefb45b988dd05/view/movie/M/302430967.mp4","http://vt1.doubanio.com/201903200945/30050295719fd9051c587fe6e7df27a4/view/movie/M/302380625.mp4"]
     * collect_count : 38096
     * images : {"small":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg","large":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg","medium":"http://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg"}
     * douban_site :
     * year : 2018
     * popular_comments : [{"rating":{"max":5,"value":1,"min":0},"useful_count":560,"author":{"uid":"149734944","avatar":"http://img1.doubanio.com/icon/u149734944-25.jpg","signature":"Do your best.","alt":"https://www.douban.com/people/149734944/","id":"149734944","name":"黛比兔"},"subject_id":"27624661","content":"台灣現在只能拍出這種片了嗎⋯⋯在電影院裡看的尬死了","created_at":"2019-02-13 11:48:37","id":"1672508710"},{"rating":{"max":5,"value":1,"min":0},"useful_count":892,"author":{"uid":"kejinlong","avatar":"http://img3.doubanio.com/icon/u37507203-116.jpg","signature":"感谢你记得。","alt":"https://www.douban.com/people/kejinlong/","id":"37507203","name":"有心打扰"},"subject_id":"27624661","content":"1.剧本牛逼死了，爱情观非常超前，冒着极大风险挑战着传统世俗观念，可能很多观众一时都难以接受，将爱情中灵与肉、爱与性彻底分隔，讲述着何为「真爱」。真爱不就是相爱的直男直女同居同床十几年，却依旧冰清玉洁吗？真爱不就是为了彼此，一方可以放弃拥有，一方愿为成其渣女，奉上自己的婚姻和身体吗？如果这都不是真爱，那何为「真爱」？2.前面的剧情非常狗血，但在真相揭晓时，所有喝下的狗血都变成同场少男少女们感动流出的泪水；3.另外还得夸一下选角，每一个演员都演得那么深演得那么认真，非常敬业，非常不容易。","created_at":"2019-03-09 17:52:12","id":"1709569370"},{"rating":{"max":5,"value":3,"min":0},"useful_count":766,"author":{"uid":"wangranran","avatar":"http://img3.doubanio.com/icon/u1240460-27.jpg","signature":"总在半夜醒来，又在黎明睡去\u2026\u2026","alt":"https://www.douban.com/people/wangranran/","id":"1240460","name":"惘然"},"subject_id":"27624661","content":"这部电影告诉我们：女孩子，如果有个男人躺在你旁边十年都没有碰你，那他一定有病。#继日本电影之后，韩国电影本土化也是水土不服#","created_at":"2019-03-12 17:04:35","id":"1713679283"},{"rating":{"max":5,"value":0,"min":0},"useful_count":287,"author":{"uid":"macaob","avatar":"http://img1.doubanio.com/icon/u1229726-3.jpg","signature":"","alt":"https://www.douban.com/people/macaob/","id":"1229726","name":"chaokoiwang"},"subject_id":"27624661","content":"神經病","created_at":"2018-12-24 23:29:32","id":"1590104502"}]
     * alt : https://movie.douban.com/subject/27624661/
     * id : 27624661
     * mobile_url : https://movie.douban.com/subject/27624661/mobile
     * photos_count : 134
     * pubdate : 2019-03-14
     * title : 比悲伤更悲伤的故事
     * do_count : null
     * has_video : false
     * share_url : http://m.douban.com/movie/subject/27624661
     * seasons_count : null
     * languages : ["汉语普通话"]
     * schedule_url : https://movie.douban.com/subject/27624661/cinema/
     * writers : [{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1376030118.5.jpg"},"name_en":"Lungshi Lü","name":"吕安弦","alt":"https://movie.douban.com/celebrity/1332731/","id":"1332731"}]
     * pubdates : ["2018-11-30(台湾)","2019-03-14(中国大陆)"]
     * website :
     * tags : ["爱情","悲伤","台湾","感动","恋爱","文艺","青春","2018","人生","成长"]
     * has_schedule : true
     * durations : ["105分钟"]
     * genres : ["爱情"]
     * collection : null
     * trailers : [{"medium":"http://img1.doubanio.com/img/trailer/medium/2550813680.jpg?1552626843","title":"预告片：低估爱情版 (中文字幕)","subject_id":"27624661","alt":"https://movie.douban.com/trailer/244500/","small":"http://img1.doubanio.com/img/trailer/small/2550813680.jpg?1552626843","resource_url":"http://vt1.doubanio.com/201903200945/2a673c61c8fc9338b73c61379cf0cb55/view/movie/M/302440500.mp4","id":"244500"},{"medium":"http://img1.doubanio.com/img/trailer/medium/2550713684.jpg?1552642482","title":"预告片 (中文字幕)","subject_id":"27624661","alt":"https://movie.douban.com/trailer/244427/","small":"http://img1.doubanio.com/img/trailer/small/2550713684.jpg?1552642482","resource_url":"http://vt1.doubanio.com/201903200945/aefad791814cb6b32b32a124ab00c700/view/movie/M/302440427.mp4","id":"244427"},{"medium":"http://img3.doubanio.com/img/trailer/medium/2549521778.jpg?1551419713","title":"大陆预告片：定档版 (中文字幕)","subject_id":"27624661","alt":"https://movie.douban.com/trailer/243854/","small":"http://img3.doubanio.com/img/trailer/small/2549521778.jpg?1551419713","resource_url":"http://vt1.doubanio.com/201903200945/fd2132c953dc86b4e9c037b4783125e8/view/movie/M/302430854.mp4","id":"243854"},{"medium":"http://img1.doubanio.com/img/trailer/medium/2540028255.jpg?","title":"预告片","subject_id":"27624661","alt":"https://movie.douban.com/trailer/239491/","small":"http://img1.doubanio.com/img/trailer/small/2540028255.jpg?","resource_url":"http://vt1.doubanio.com/201903200945/f6bdc296e4269155715b83c1c8fe23e1/view/movie/M/302390491.mp4","id":"239491"}]
     * episodes_count : null
     * trailer_urls : ["http://vt1.doubanio.com/201903200945/2a673c61c8fc9338b73c61379cf0cb55/view/movie/M/302440500.mp4","http://vt1.doubanio.com/201903200945/aefad791814cb6b32b32a124ab00c700/view/movie/M/302440427.mp4","http://vt1.doubanio.com/201903200945/fd2132c953dc86b4e9c037b4783125e8/view/movie/M/302430854.mp4","http://vt1.doubanio.com/201903200945/f6bdc296e4269155715b83c1c8fe23e1/view/movie/M/302390491.mp4"]
     * has_ticket : true
     * bloopers : [{"medium":"http://img1.doubanio.com/img/trailer/medium/2550536021.jpg?1552648268","title":"花絮：导演特辑 (中文字幕)","subject_id":"27624661","alt":"https://movie.douban.com/trailer/244307/","small":"http://img1.doubanio.com/img/trailer/small/2550536021.jpg?1552648268","resource_url":"http://vt1.doubanio.com/201903200945/e560117381e1b1b46294593251df2447/view/movie/M/302440307.mp4","id":"244307"},{"medium":"http://img1.doubanio.com/img/trailer/medium/2540307241.jpg?","title":"花絮 (中文字幕)","subject_id":"27624661","alt":"https://movie.douban.com/trailer/239582/","small":"http://img1.doubanio.com/img/trailer/small/2540307241.jpg?","resource_url":"http://vt1.doubanio.com/201903200945/82170c38de26bd0e04e94d64647e6184/view/movie/M/302390582.mp4","id":"239582"},{"medium":"http://img1.doubanio.com/img/trailer/medium/2549830012.jpg?1551694236","title":"MV：A-Lin献唱主题曲《有一种悲伤》 (中文字幕)","subject_id":"27624661","alt":"https://movie.douban.com/trailer/243967/","small":"http://img1.doubanio.com/img/trailer/small/2549830012.jpg?1551694236","resource_url":"http://vt1.doubanio.com/201903200945/c364836c80a7d596ceeefb45b988dd05/view/movie/M/302430967.mp4","id":"243967"},{"medium":"http://img1.doubanio.com/img/trailer/medium/2538502534.jpg?","title":"MV：主題曲《有一種悲傷》 (中文字幕)","subject_id":"27624661","alt":"https://movie.douban.com/trailer/238625/","small":"http://img1.doubanio.com/img/trailer/small/2538502534.jpg?","resource_url":"http://vt1.doubanio.com/201903200945/30050295719fd9051c587fe6e7df27a4/view/movie/M/302380625.mp4","id":"238625"}]
     * clip_urls : []
     * current_season : null
     * casts : [{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31663.jpg"},"name_en":"Ivy Chen","name":"陈意涵","alt":"https://movie.douban.com/celebrity/1274316/","id":"1274316"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511061580.88.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511061580.88.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511061580.88.jpg"},"name_en":"Jasper Liu","name":"刘以豪","alt":"https://movie.douban.com/celebrity/1326546/","id":"1326546"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31369.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31369.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p31369.jpg"},"name_en":"Bryan Shu-Hao Chang","name":"张书豪","alt":"https://movie.douban.com/celebrity/1315045/","id":"1315045"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1548413024.58.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1548413024.58.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1548413024.58.jpg"},"name_en":"Annie Chen","name":"陈庭妮","alt":"https://movie.douban.com/celebrity/1317166/","id":"1317166"}]
     * countries : ["台湾"]
     * mainland_pubdate : 2019-03-14
     * photos : [{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2550813096.jpg","image":"https://img3.doubanio.com/view/photo/l/public/p2550813096.jpg","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2550813096.jpg","alt":"https://movie.douban.com/photos/photo/2550813096/","id":"2550813096","icon":"https://img3.doubanio.com/view/photo/s/public/p2550813096.jpg"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2550813090.jpg","image":"https://img3.doubanio.com/view/photo/l/public/p2550813090.jpg","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2550813090.jpg","alt":"https://movie.douban.com/photos/photo/2550813090/","id":"2550813090","icon":"https://img3.doubanio.com/view/photo/s/public/p2550813090.jpg"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2533230465.jpg","image":"https://img3.doubanio.com/view/photo/l/public/p2533230465.jpg","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2533230465.jpg","alt":"https://movie.douban.com/photos/photo/2533230465/","id":"2533230465","icon":"https://img3.doubanio.com/view/photo/s/public/p2533230465.jpg"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2533230253.jpg","image":"https://img3.doubanio.com/view/photo/l/public/p2533230253.jpg","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2533230253.jpg","alt":"https://movie.douban.com/photos/photo/2533230253/","id":"2533230253","icon":"https://img3.doubanio.com/view/photo/s/public/p2533230253.jpg"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2551078972.jpg","image":"https://img3.doubanio.com/view/photo/l/public/p2551078972.jpg","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2551078972.jpg","alt":"https://movie.douban.com/photos/photo/2551078972/","id":"2551078972","icon":"https://img3.doubanio.com/view/photo/s/public/p2551078972.jpg"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2551078971.jpg","image":"https://img3.doubanio.com/view/photo/l/public/p2551078971.jpg","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2551078971.jpg","alt":"https://movie.douban.com/photos/photo/2551078971/","id":"2551078971","icon":"https://img3.doubanio.com/view/photo/s/public/p2551078971.jpg"},{"thumb":"https://img3.doubanio.com/view/photo/m/public/p2551078970.jpg","image":"https://img3.doubanio.com/view/photo/l/public/p2551078970.jpg","cover":"https://img3.doubanio.com/view/photo/sqs/public/p2551078970.jpg","alt":"https://movie.douban.com/photos/photo/2551078970/","id":"2551078970","icon":"https://img3.doubanio.com/view/photo/s/public/p2551078970.jpg"},{"thumb":"https://img1.doubanio.com/view/photo/m/public/p2551078969.jpg","image":"https://img1.doubanio.com/view/photo/l/public/p2551078969.jpg","cover":"https://img1.doubanio.com/view/photo/sqs/public/p2551078969.jpg","alt":"https://movie.douban.com/photos/photo/2551078969/","id":"2551078969","icon":"https://img1.doubanio.com/view/photo/s/public/p2551078969.jpg"},{"thumb":"https://img1.doubanio.com/view/photo/m/public/p2551078968.jpg","image":"https://img1.doubanio.com/view/photo/l/public/p2551078968.jpg","cover":"https://img1.doubanio.com/view/photo/sqs/public/p2551078968.jpg","alt":"https://movie.douban.com/photos/photo/2551078968/","id":"2551078968","icon":"https://img1.doubanio.com/view/photo/s/public/p2551078968.jpg"},{"thumb":"https://img1.doubanio.com/view/photo/m/public/p2551078967.jpg","image":"https://img1.doubanio.com/view/photo/l/public/p2551078967.jpg","cover":"https://img1.doubanio.com/view/photo/sqs/public/p2551078967.jpg","alt":"https://movie.douban.com/photos/photo/2551078967/","id":"2551078967","icon":"https://img1.doubanio.com/view/photo/s/public/p2551078967.jpg"}]
     * summary : 唱片制作人张哲凯(刘以豪)和王牌作词人宋媛媛(陈意涵)相依为命，两人自幼身世坎坷只有彼此为伴，他们是亲人、是朋友，也彷佛是命中注定的另一半。父亲罹患遗传重症而被母亲抛弃的哲凯，深怕自己随时会发病不久人世，始终没有跨出友谊的界线对媛媛展露爱意。眼见哲凯的病情加重，他暗自决定用剩余的生命完成他们之间的终曲，再为媛媛找个可以托付一生的好男人。这时，事业有 成温柔体贴的医生(张书豪)适时的出现让他成为照顾媛媛的最佳人选，二人按部就班发展着关系。一切看似都在哲凯的计划下进行。然而，故事远比这里所写更要悲伤......
     * clips : []
     * subtype : movie
     * directors : [{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44818.jpg"},"name_en":"Hsiao Chien Lin","name":"林孝谦","alt":"https://movie.douban.com/celebrity/1312860/","id":"1312860"}]
     * comments_count : 16966
     * popular_reviews : [{"rating":{"max":5,"value":2,"min":0},"title":"《比悲傷更悲傷的故事》／無病呻吟的悲傷！","subject_id":"27624661","author":{"uid":"sunline","avatar":"http://img1.doubanio.com/icon/u1336357-4.jpg","signature":"","alt":"https://www.douban.com/people/sunline/","id":"1336357","name":"換日線"},"summary":"我很喜歡這部戲的演員，從劉以豪、陳意涵、張書豪、布魯斯（幹嘛改名啊！）以及這部戲野性狂放、美到讓人驚豔的陳庭妮。並且第一次發現布魯斯的聲線真是好聽。 只是這個故事如我預期的一模一樣，完全是我無法忍受...","alt":"https://movie.douban.com/review/9851170/","id":"9851170"},{"rating":{"max":5,"value":1,"min":0},"title":"片名应该改为变态爱情故事","subject_id":"27624661","author":{"uid":"1183701","avatar":"http://img1.doubanio.com/icon/u1183701-120.jpg","signature":"sth new sth old just sth","alt":"https://www.douban.com/people/1183701/","id":"1183701","name":"中年moulala"},"summary":"住了十几年却说两个人是朋友，只有亲亲和抱抱的关系，文艺片子也不能这样误导观众，我觉得最悲伤的是男主在结束生命之前也没有好好享受和女主的男欢女爱，而是想着法子去设计女主的爱情和干涉另外一对可怜的情侣...","alt":"https://movie.douban.com/review/10049513/","id":"10049513"},{"rating":{"max":5,"value":1,"min":0},"title":"现在是连台湾导演和制片人，都觉得内地票房容易赚吗？","subject_id":"27624661","author":{"uid":"siyecaomifeng","avatar":"http://img1.doubanio.com/icon/u4614773-5.jpg","signature":"","alt":"https://www.douban.com/people/siyecaomifeng/","id":"4614773","name":"小蜜蜂嗡嗡嗡"},"summary":"观众眼睛是雪亮的，见到各位的回复很是欣慰，身为爱电影的影迷又写了一篇2千万元制作的不及格故事，恭喜取得好票房突破3亿元 请问豆瓣啥时候可以打负分呢？那些打5星的评评语，好明显呀，都是统一免费看片被要求...","alt":"https://movie.douban.com/review/10039949/","id":"10039949"},{"rating":{"max":5,"value":2,"min":0},"title":"事儿逼拆婚记","subject_id":"27624661","author":{"uid":"94332979","avatar":"http://img1.doubanio.com/icon/u94332979-5.jpg","signature":"不如我们从头来过","alt":"https://www.douban.com/people/94332979/","id":"94332979","name":"我是尾号2473"},"summary":"有这样一名医生，他人帅多金，体贴温柔，家世又好。只可惜在短短一年内，这名医生遭受到两次重大，哦不，是毁灭性的情感打击： 第一次，你发现和爱情长跑六年且已经订婚的未婚妻，把你给绿了，而且还不止一次 第...","alt":"https://movie.douban.com/review/10044134/","id":"10044134"},{"rating":{"max":5,"value":1,"min":0},"title":"get不到陈意涵的美，更get不到泪点。","subject_id":"27624661","author":{"uid":"155601158","avatar":"http://img3.doubanio.com/icon/u155601158-7.jpg","signature":"","alt":"https://www.douban.com/people/155601158/","id":"155601158","name":"猫咪薄荷"},"summary":"我其实是个比较感性的人，是容易被感动的。 或许是我心情不够好，所以总觉得电影不够动人。 我始终无法走入剧情，我尽力让自己产生共鸣。有些角色还显得多余，比如那个唱猫咪歌曲的女孩子，包括一开始她和宋媛媛...","alt":"https://movie.douban.com/review/10043155/","id":"10043155"},{"rating":{"max":5,"value":3,"min":0},"title":"《比悲伤更悲伤的故事》影评：心机BOY与绿茶婊的那些事儿","subject_id":"27624661","author":{"uid":"133516091","avatar":"http://img3.doubanio.com/icon/u133516091-7.jpg","signature":"微信公众号：吉橙君","alt":"https://www.douban.com/people/133516091/","id":"133516091","name":"吉橙君"},"summary":"韩式悲情三要素：车祸癌症治不好。 3.14上映的台湾爱情电影《比悲伤更悲伤的故事》，就是翻拍自韩国同名电影。聪明如你，光看标题就能猜到了吧，三要素一个都不会少。 他，张哲凯，16岁那年，生母因他患有癌症，...","alt":"https://movie.douban.com/review/10041330/","id":"10041330"},{"rating":{"max":5,"value":4,"min":0},"title":"这个故事不是关于爱情","subject_id":"27624661","author":{"uid":"162955594","avatar":"http://img1.doubanio.com/icon/u162955594-4.jpg","signature":"","alt":"https://www.douban.com/people/162955594/","id":"162955594","name":"K"},"summary":"我看前半段的时候在想，这是多么drama，多么自以为是的两个人，暗自揣测各自伤神。直到到最后一段Cream的独白，那段在隧道里看着K上了爸爸妈妈的车声嘶力竭的奔跑，和最后她的决定，我突然明白，这早就超好爱情的...","alt":"https://movie.douban.com/review/9822173/","id":"9822173"},{"rating":{"max":5,"value":3,"min":0},"title":"《比悲伤更悲伤的故事》有哪些细节打动了你？","subject_id":"27624661","author":{"uid":"xiaoyaozizai","avatar":"http://img1.doubanio.com/icon/u1892408-5.jpg","signature":"今天大客栈（jtd1024）","alt":"https://www.douban.com/people/xiaoyaozizai/","id":"1892408","name":"今天道"},"summary":"1.故事大概如此：男主和女主在一起十多年，一起吃一起喝一起睡；男主发现自己要死了，就给女主找了个牙医老公；女主假装自己不知道男主要死了，跟男二结了婚；最后女主觉醒，离开了男二，和男一一起服毒自杀\u2014\u2014...","alt":"https://movie.douban.com/review/10047505/","id":"10047505"},{"rating":{"max":5,"value":2,"min":0},"title":"我们为什么感动不了了？","subject_id":"27624661","author":{"uid":"Oumuamua","avatar":"http://img3.doubanio.com/icon/u48138357-16.jpg","signature":"大海啊你全是水，骏马啊你四条腿","alt":"https://www.douban.com/people/Oumuamua/","id":"48138357","name":"小人儿"},"summary":"车祸、癌症、为爱牺牲。。。曾几何时，这样的故事总能赚的观众大把大把的眼泪。然而，在如今，这样的故事已经不再能使观众潸然泪下了。是故事太鸡血，剧情太俗套，还是我们审美疲劳？抑或者是我们太铁石心肠？ 以...","alt":"https://movie.douban.com/review/10043453/","id":"10043453"},{"rating":{"max":5,"value":2,"min":0},"title":"预料之中的高票房，最后拿了票房冠军。","subject_id":"27624661","author":{"uid":"kitajimajunko","avatar":"http://img3.doubanio.com/icon/u53943130-107.jpg","signature":"向上を求めない淳子。","alt":"https://www.douban.com/people/kitajimajunko/","id":"53943130","name":"北岛淳子"},"summary":"悲伤不是两个人相爱却不能在一起，而是对于即将发生的一切无能为力；比悲伤更悲伤的不是他的隐瞒，而是你明明已经知道了却要陪他演戏，是两个人都不能坦白。 四个月前在金马影展看了台版《比悲伤更悲伤的故事》全...","alt":"https://movie.douban.com/review/10042637/","id":"10042637"}]
     * ratings_count : 35759
     * aka : ["More Than Blue"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String original_title;
    private int collect_count;
    private ImagesBean images;
    private String douban_site;
    private String year;
    private String alt;
    private String id;
    private String mobile_url;
    private int photos_count;
    private String pubdate;
    private String title;
    private Object do_count;
    private boolean has_video;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private String website;
    private boolean has_schedule;
    private Object collection;
    private Object episodes_count;
    private boolean has_ticket;
    private Object current_season;
    private String mainland_pubdate;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<?> videos;
    private List<String> blooper_urls;
    private List<PopularCommentsBean> popular_comments;
    private List<String> languages;
    private List<WritersBean> writers;
    private List<String> pubdates;
    private List<String> tags;
    private List<String> durations;
    private List<String> genres;
    private List<TrailersBean> trailers;
    private List<String> trailer_urls;
    private List<BloopersBean> bloopers;
    private List<?> clip_urls;
    private List<CastsBean> casts;
    private List<String> countries;
    private List<PhotosBean> photos;
    private List<?> clips;
    private List<DirectorsBean> directors;
    private List<PopularReviewsBean> popular_reviews;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isHas_schedule() {
        return has_schedule;
    }

    public void setHas_schedule(boolean has_schedule) {
        this.has_schedule = has_schedule;
    }

    public Object getCollection() {
        return collection;
    }

    public void setCollection(Object collection) {
        this.collection = collection;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public boolean isHas_ticket() {
        return has_ticket;
    }

    public void setHas_ticket(boolean has_ticket) {
        this.has_ticket = has_ticket;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getMainland_pubdate() {
        return mainland_pubdate;
    }

    public void setMainland_pubdate(String mainland_pubdate) {
        this.mainland_pubdate = mainland_pubdate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<?> getVideos() {
        return videos;
    }

    public void setVideos(List<?> videos) {
        this.videos = videos;
    }

    public List<String> getBlooper_urls() {
        return blooper_urls;
    }

    public void setBlooper_urls(List<String> blooper_urls) {
        this.blooper_urls = blooper_urls;
    }

    public List<PopularCommentsBean> getPopular_comments() {
        return popular_comments;
    }

    public void setPopular_comments(List<PopularCommentsBean> popular_comments) {
        this.popular_comments = popular_comments;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<WritersBean> getWriters() {
        return writers;
    }

    public void setWriters(List<WritersBean> writers) {
        this.writers = writers;
    }

    public List<String> getPubdates() {
        return pubdates;
    }

    public void setPubdates(List<String> pubdates) {
        this.pubdates = pubdates;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailersBean> trailers) {
        this.trailers = trailers;
    }

    public List<String> getTrailer_urls() {
        return trailer_urls;
    }

    public void setTrailer_urls(List<String> trailer_urls) {
        this.trailer_urls = trailer_urls;
    }

    public List<BloopersBean> getBloopers() {
        return bloopers;
    }

    public void setBloopers(List<BloopersBean> bloopers) {
        this.bloopers = bloopers;
    }

    public List<?> getClip_urls() {
        return clip_urls;
    }

    public void setClip_urls(List<?> clip_urls) {
        this.clip_urls = clip_urls;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public List<?> getClips() {
        return clips;
    }

    public void setClips(List<?> clips) {
        this.clips = clips;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<PopularReviewsBean> getPopular_reviews() {
        return popular_reviews;
    }

    public void setPopular_reviews(List<PopularReviewsBean> popular_reviews) {
        this.popular_reviews = popular_reviews;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

}




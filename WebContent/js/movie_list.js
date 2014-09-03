function pau()
{
 var video = document.getElementById("Video1");
 video.pause();
 }

function play_it()//播放视频
{
    
	
	if(arguments[2]==1)
	{
		var video = document.getElementById("Video1");
		video.style.display="block";
		document.getElementById("Video2").style.display="none";
	}
	else if(arguments[2]==2)
	{
		var video = document.getElementById("Video2");
		video.style.display="block";
		document.getElementById("Video1").style.display="none";
	}
    

	//video.src = "../video/demo" + (arguments[2]-1) + ".mp4";
	
    
	var startTime=parseFloat(arguments[0]);  
	var endTime=parseFloat(arguments[1]); 
	//setTimeout(function(){video.currentTime = startTime;},500);
	video.currentTime = startTime;
	video.play();
	//video.currentTime = startTime;
	setTimeout("pau()",(endTime-startTime)*1000);
}

function changecolorfunction()
{      
	    var word = 'his';
	    var table = document.getElementById('j_idt31:0:sentence');

	    var sentence = "A moment before he closed his eyes...";
        var changecolor=table.innerHTML;
		var trans=sentence.toLocaleLowerCase();
		trans=" "+trans+" ";
		trans=trans.replace(/[\ |\~|\`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\-|\_|\+|\=|\||\\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?]/g," "); 

		var wstart = trans.indexOf(" "+word+" ");
        var len = word.length;
        var whtml = '<font color = "#333333" >' + sentence.substring(0,wstart) +"</font>" + '<font color = "#0099ff" >' + sentence.substring(wstart,wstart+len) + "</font>"+ sentence.substring(wstart+len);

        table.innerHTML=changecolor.replace(sentence,whtml);

}

function get()
{
   var movie_list = document.getElementById('movieList');
 //  alert(movie_list.id);
   movie_list.style = 'display:block'
  // loadXMLDoc();
}

function collect(img_id)//收藏
{
    var img=document.getElementById(img_id);
    var child=img.parentNode.childNodes;
    for(n=0;n<child.length;n++)
    {
    	//alert(child[n].className);
    	if(child[n].className=="iceCmdBtn movie_list_result_btnLike"||child[n].className=="iceCmdBtn overlays_result_btnLike")
    	{
    		img=child[n];
    	}
    }
	if (img.style.backgroundImage == 'url("../image/like-02.png")') 
    {
        img.style.backgroundImage = 'url("../image/like-01.png")';
    } 
	else 
	{
	    img.style.backgroundImage = 'url("../image/like-02.png")';
	}
}

function Appearoverlays(start,end,movieid)
{
	play_it(start,end,movieid);
	document.getElementById("overlays").style.display="block";
}

function closeOverL()//浮层显示函数
{
	document.getElementById("overlays").style.display="none"
}

function hidden_sentences(isShow,id)
{
	if(isShow==false)
		document.getElementById(id).style.display="none";
	else if(isShow==true)
		document.getElementById(id).style.display="block";
}

function search(word)
{
	//alert(word);
	var input=document.getElementById('search_form:search_input').value=word;

	
}
function pressEnter(evt)//键盘监听
{
    var evt=evt || event;
    if(evt.keyCode==13) 
	{
        if(input=document.getElementById('search_form:search_input').value=="")alert("请先输入单词");
    }
	
}

function noUserclick(User)
{
	if(User==null||User=="")alert("若要收藏 请先登录");
}
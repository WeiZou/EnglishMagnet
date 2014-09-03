var type;
var age;
var nation;
function verify_actor_text(a)
{
	var text_id="movie_type_form:"+a;
	if(document.getElementById(text_id).value=="")
	alert("请先输入演员名");	
}

function clear_actor(a)
{
	text_id="movie_type_form:"+a;
	document.getElementById(text_id).value="";
	}

function showChoice(menu_type,choice)
{
	var form_id='movie_type_form';
	var choicedisplay_id='movie_type_form:displayChoice';
	var panelNode=document.getElementById(choicedisplay_id);
	var choice_nodes=panelNode.childNodes;
	var node_id=form_id+':movie_type_mb:movie_type_'+menu_type+':movie_type_'+choice+':out';
	var node=document.getElementById(node_id);
	
	if(menu_type=='type')
	{
		if( type==null && choice!='type_all')
		{
			var newdiv=choice_nodes[0];
		    newdiv.style.display='block';
			var textNode=document.createTextNode(node.firstChild.data);
			newdiv.appendChild(textNode);
			type="movie_type_form:movie_type_type_choice";
			}
		else{
			if(choice.match('all')){
				document.getElementById(type).innerHTML='';
				document.getElementById(type).style.display='none';
			}
			else{document.getElementById(type).innerHTML=node.firstChild.data;
			document.getElementById(type).style.display='block';}
		}
	}
	
	if(menu_type=='nation')
	{
		if( nation==null && choice!='nation_all' )
		{
			var newdiv=choice_nodes[1];
		    newdiv.style.display='block';
			var textNode=document.createTextNode(node.firstChild.data);
			newdiv.appendChild(textNode);
			nation="movie_type_form:movie_type_nation_choice";
			}
		else{
			if(choice.match('all')){
				document.getElementById(nation).innerHTML='';
				document.getElementById(nation).style.display='none';
			}
				
			else{document.getElementById(nation).innerHTML=node.firstChild.data;
			document.getElementById(nation).style.display='block';}
		}
		}
	if(menu_type=='age')
	{
		if( age==null &&choice!='age_all')
		{
			var newdiv=choice_nodes[2];
		    newdiv.style.display='block';
			var textNode=document.createTextNode(node.firstChild.data);
			newdiv.appendChild(textNode);
			age="movie_type_form:movie_type_age_choice";
			}
		else{
			if(choice.match('all')){
				document.getElementById(age).innerHTML='';
				//type='all';
				document.getElementById(age).style.display='none';
			}
			else{document.getElementById(age).innerHTML=node.firstChild.data;
			document.getElementById(age).style.display='block';}
		}
		}
}
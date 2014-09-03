
function trim(s)
{return s.replace(/^\s+|\s+$/g,"");};

function verify_word_text()
{
	var word_id="search_form:search_input";
	var word_trim=trim(document.getElementById(word_id).value);
	if(word_trim=="")
		{
		alert("请先输入单词");		
		}
}

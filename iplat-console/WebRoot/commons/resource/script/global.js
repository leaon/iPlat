/**
 * 系统公共js类库。
 * 
 * @author Leaon
 *
 * Date						Author				Description			
 * ---------------------------------------------------------------------------------------------
 * 2012-08-17				Leaon				创建StringUtil.js。
 *
 */
function Global(){
	
	window["MzBrowser"]={};(function()
{
  if(MzBrowser.platform) return;
  var ua = window.navigator.userAgent;
  MzBrowser.platform = window.navigator.platform;

  MzBrowser.firefox = ua.indexOf("Firefox")>0;
  MzBrowser.opera = typeof(window.opera)=="object";
  MzBrowser.ie = !MzBrowser.opera && ua.indexOf("MSIE")>0;
  MzBrowser.mozilla = window.navigator.product == "Gecko";
  MzBrowser.netscape= window.navigator.vendor=="Netscape";
  MzBrowser.gecko = ua.indexOf('Gecko')>-1 && ua.indexOf('KHTML')==-1;
  MzBrowser.safari  = ua.indexOf("Safari")>-1;

  if(MzBrowser.firefox) var re = /Firefox(/s|// )(/d+(/./d+)?)/;
  else if(MzBrowser.ie) var re = /MSIE( )(/d+(/./d+)?)/;
  else if(MzBrowser.opera) var re = /Opera(/s|// )(/d+(/./d+)?)/;
  else if(MzBrowser.netscape) var re = /Netscape(/s|// )(/d+(/./d+)?)/;
  else if(MzBrowser.safari) var re = /Version(//)(/d+(/./d+)?)/;
  else if(MzBrowser.mozilla) var re = /rv(/:)(/d+(/./d+)?)/;

  if("undefined"!=typeof(re)&&re.test(ua))
    MzBrowser.version = parseFloat(RegExp.$2);
})(); 

//调用的时候可以通过 MzBrowser.ie 、MzBrowser.firefox 等 true/false 的判断条件；而通过 MzBrowser.version 可以直接得到当前浏览器的大版本号。 
	
	/**
	 * 将Global类加载到当前DOM。
	 */
	document.write("var global = new Global();");
	
}

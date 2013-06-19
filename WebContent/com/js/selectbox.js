// SaySelect Environment Constants

var SS_ENV = new Object();
SS_ENV.IE_Version = new Number(((window.navigator.appVersion.split('; '))[1].split(' '))[1]);
SS_ENV.CR = new Object();
SS_ENV.CR.ReverseBackground = '#F1EEE8';
SS_ENV.CR.ReverseText = '999999';
SS_ENV.CR.Border = '#ffffff';
SS_ENV.CR.BorderActive = '#ffffff';
SS_ENV.ImgPrefix = 'images/';
SS_ENV.DefaultHeight =15;
SS_ENV.ButtonWidth = 15;
SS_ENV.OptionsDivStyle = ''
  + ' display:none;'
  + ' z-index:10;'
  + ' position:absolute;'
  + ' border:1 solid '+ SS_ENV.CR.Border+';'
  + ' background-color:#ffffff;'
  + ' scrollbar-face-color:#ffffff;'
  + ' scrollbar-shadow-color:F1EEE8;'
  + ' scrollbar-highlight-color:#ffffff;'
  + ' scrollbar-3dlight-color:E2DED6;'
  + ' scrollbar-darkshadow-color:#ffffff;'
  + ' scrollbar-track-color:#F1EEE8;'
  + ' scrollbar-arrow-color:#B3B3B3;';
SS_ENV.MouseStyle = 'cursor:hand;'//박스위에올라갔을때의 마우스모양
SS_ENV.OptionNobrStyle = ''
  + ' font-size:11px; padding-bottom:2; color: gray;'
  + ' font-family:돋움;';
// SaySelect Variables
var SS_VAR = new Object();
SS_VAR.DivDummy = document.createElement("DIV");
SS_VAR.SelectList = new Array();
SS_VAR.bEventAttached = false;

var SS_CreatedElements = new Object();

function unloadObjects()
{
  try {
    if (SS_VAR && SS_VAR.SelectList)
    {
      for (key in SS_VAR.SelectList)
      {
	if (SS_VAR.SelectList[key])
	{
	  try {
	   SS_VAR.SelectList[key].select.setAttribute('SS', 0);
	  } catch (e) {};
	  delete SS_VAR.SelectList[key];
	}
      }
    }
  } catch (e) {};
}

attachEvent("onunload", unloadObjects);

function SS_create (srcHTML, ListMax, bAutoDetect)
{
  // property
  this.ssID = SS_VAR.SelectList.length;
  this.bOriginalSelect = (bAutoDetect && SS_ENV.IE_Version < 5.5);
  this.select = SS_createElement(srcHTML);
  this.selectedIndex = this.select.selectedIndex;
  this.options = this.select.options;
  this.width = parseInt(this.select.style.width);
  this.height = (this.select.style.height) ? parseInt(this.select.style.height) : SS_ENV.DefaultHeight;
  this.OptionHeight = this.height ;
  this.bListDown = (ListMax && '-'==ListMax.toString().substr(0, 1)) ? false : true;
  this.ListMax = (!isNaN(parseInt(ListMax))) ? Math.abs(ListMax) : 100;

  this.Table;
  this.TitleDiv;
  this.TitleTable;
  this.TitleWrapper;
  this.OptionsDiv;
  this.OptionsWrapper;
  this.OptionsTable;
  this.bFocused = false;
  this.bExpanded = false;
  this.bReverse = false;

  // private method
  this.isThisEventToBeCanceled = SS_isThisEventToBeCanceled;
  this.toggleTitle = SS_toggleTitle;
  this.syncSelectedIndex = SS_syncSelectedIndex;
  this.toggleOptions = SS_toggleOptions;
  this.turnOnOption = SS_turnOnOption;
  this.turnOffOption = SS_turnOffOption;
  this.handleMousewheel = SS_handleMousewheel;
  this.handleOverTitle = SS_handleOverTitle;
  this.handleOutTitle = SS_handleOutTitle;
  this.handleOverOption = SS_handleOverOption;
  this.createTable = SS_createTable;
  this.createTitleDiv = SS_createTitleDiv;
  this.createOptionsDiv = SS_createOptionsDiv;
  this.createOptionTr = SS_createOptionTr;
  this.adjustOptionsDiv = SS_adjustOptionsDiv;
  this.syncOptions = SS_syncOptions;
  this.pressOption = SS_pressOption;
  this.moveOption = SS_moveOption;
  this.releaseOption = SS_releaseOption;
  this.pressTitle = SS_pressTitle;
  this.releaseTitle = SS_releaseTitle;

  // public method
  this.display = SS_display;
  this.insertOption = SS_insertOption;
  this.deleteOption = SS_deleteOption;
  this.changeOption = SS_changeOption;

  // initiate
  this.createTable();
  this.select.setAttribute('SS', this);
  if (!this.bOriginalSelect)
    this.select.onpropertychange = SS_handlePropertychange;
  SS_VAR.SelectList[this.ssID] = this;
}
function SS_display ()
{
  document.write("<div id=SS_TempDiv></div>\n");
  document.all.SS_TempDiv.appendChild(this.Table);
  document.all.SS_TempDiv.removeNode();
}
function SS_write (srcHTML, ListMax, bAutoDetect)
{
  var oSS = new SS_create(srcHTML, ListMax, bAutoDetect);
  oSS.display();
  return oSS;
}
function SS_insertOption (value, innerText, idx)
{
  var NewOption = document.createElement("OPTION");
  SS_CreatedElements[SS_CreatedElements.length] = NewOption;
  this.options.add(NewOption, idx);
  NewOption.innerText = innerText;
  NewOption.value = value;

  if (!this.bOriginalSelect)
    this.createOptionTr(idx);
  this.syncOptions();
  this.adjustOptionsDiv();
  this.syncSelectedIndex();
}
function SS_deleteOption (idx)
{
  this.options.remove(idx);
  if (!this.bOriginalSelect)
    this.OptionsTable.deleteRow(idx);
  this.syncOptions();
  this.adjustOptionsDiv();
  this.syncSelectedIndex();
}
function SS_changeOption (idx, value, innerText)
{
  this.options[idx].value = value;
  this.options[idx].innerText = innerText;
  this.syncOptions();
  this.syncSelectedIndex();
}

function SS_insertOption1 (obj, value, innerText, idx)
{
  var NewOption = document.createElement("OPTION");
  SS_CreatedElements[SS_CreatedElements.length] = NewOption;
  obj.options.add(NewOption, idx);
  NewOption.innerText = innerText;
  NewOption.value = value;

  if (!obj.bOriginalSelect)
    obj.createOptionTr(idx);

  obj.syncOptions();
  obj.adjustOptionsDiv();
  obj.syncSelectedIndex();
}


function SS_changeOption1 (obj, idx, value, innerText)
{
  obj.options[idx].value = value;
  obj.options[idx].innerText = innerText;
  obj.syncOptions();
  obj.syncSelectedIndex();
}

function SS_deleteOption1 (obj, idx)
{
  obj.options.remove(idx);
  if (!obj.bOriginalSelect)
    obj.OptionsTable.deleteRow(idx);
  obj.syncOptions();
  obj.adjustOptionsDiv();
  obj.syncSelectedIndex();
}


function SS_cancelEvent (event)
{
  event.cancelBubble = true;
  event.returnValue = false;
}
function SS_isThisEventToBeCanceled (event)
{
  if ('object' == typeof(event)) {
    switch (event.type) {
      case 'mousedown':
        if (!(event.button & 1)) return true;
        break;
      case 'mouseup':
        if (!(event.button & 1)) return true;
        if (SS_ENV.IE_Version >= 5.5 && event.srcElement != this.srcElementOfLastMousedown && this.srcElementOfLastMousedown != null) {
          this.srcElementOfLastMousedown = null;
          return true;
        }
        break;
      case 'mouseout':
        if (!(SS_ENV.IE_Version < 5.5 && event.srcElement == this.srcElementOfLastMousedown))
          return true;
        break;
      case 'mousemove':
        if (SS_ENV.IE_Version >= 5.5 && event.srcElement != this.srcElementOfLastMousedown && this.srcElementOfLastMousedown != null)
          return true;
        break;
    }
  }
  return false;
}
function SS_createElement (html)
{
  SS_VAR.DivDummy.insertAdjacentHTML('afterBegin', html);
  var oEl = SS_VAR.DivDummy.children(0);
  while (SS_VAR.DivDummy.children.length > 0) {
    SS_VAR.DivDummy.removeChild(SS_VAR.DivDummy.children(0));
  }
  return oEl;
}
function SS_blurExcept (except)
{
  SS_cancelEvent(window.event);

  except = ('number'==typeof(except)) ? except : -1;

  var bHasToDetachEvent = true;
  for (var i=0; i < SS_VAR.SelectList.length; i++) {
    if (-1==except && SS_VAR.SelectList[i].bFocused && SS_VAR.SelectList[i].bExpanded) {
      SS_VAR.SelectList[i].toggleOptions(false, true);
      SS_VAR.SelectList[i].toggleTitle(true);
      bHasToDetachEvent = false;
    }
    else if (i!=except) {
      if (SS_VAR.SelectList[i].bExpanded)
        SS_VAR.SelectList[i].toggleOptions(false, true);
      if (SS_VAR.SelectList[i].bReverse)
        SS_VAR.SelectList[i].toggleTitle(false);
      SS_VAR.SelectList[i].bFocused = false;
    }
  }

  if (SS_VAR.bEventAttached && bHasToDetachEvent) {
    document.detachEvent('onmousedown', SS_blurExcept);
    document.detachEvent('ondblclick', SS_blurExcept);
    SS_VAR.bEventAttached = false;
  }
}
function SS_syncSelectedIndex ()
{
  this.selectedIndex = this.select.selectedIndex;

  if (this.bOriginalSelect) return;

  if (this.TitleTable.cells(0).childNodes(0).innerText != this.options[this.selectedIndex].innerText)
    this.TitleTable.cells(0).childNodes(0).innerText = this.options[this.selectedIndex].innerText;
  if (this.bExpanded)
    this.toggleOptions(false);
}
function SS_syncSelectedIndex1 (obj)
{
  obj.selectedIndex = obj.select.selectedIndex;

  if (obj.bOriginalSelect) return;

  if (obj.TitleTable.cells(0).childNodes(0).innerText != obj.options[obj.selectedIndex].innerText)
    obj.TitleTable.cells(0).childNodes(0).innerText = obj.options[obj.selectedIndex].innerText;
  if (obj.bExpanded)
    obj.toggleOptions(false);
}
function SS_toggleTitle (bReverse)
{
  this.bReverse = ('undefined'!=typeof(bReverse)) ? bReverse: (!this.bReverse);
  this.TitleTable.cells(0).style.backgroundColor = this.bReverse ? SS_ENV.CR.ReverseBackground : '';
  this.TitleTable.cells(0).style.color = this.bReverse ? SS_ENV.CR.ReverseText : '';
}
function SS_toggleOptions (bExpanded, bStrict)
{
  if (!bStrict && !this.bFocused) {
    SS_blurExcept(this.ssID);
  }
  this.bExpanded = ('undefined'!=typeof(bExpanded)) ? bExpanded: (!this.bExpanded);
  if (this.bExpanded) {
    this.adjustOptionsDiv();
    this.OptionsDiv.style.display = 'block';
    if (!bStrict) {
      this.toggleTitle(false);
      this.handleOverOption(this.selectedIndex);
    }
    this.handleOutTitle();
  }
  else {
    this.OptionsDiv.style.display = 'none';
    if (!bStrict) {
      this.toggleTitle(true);
    }
  }
  if (!bStrict) {
    this.bFocused = true;

    if (!SS_VAR.bEventAttached) {
      document.attachEvent('onmousedown', SS_blurExcept);
      document.attachEvent('ondblclick', SS_blurExcept);
      SS_VAR.bEventAttached = true;
    }
  }
}
function SS_handlePropertychange ()
{
  if ('propertychange'==window.event.type && 'selectedIndex'==window.event.propertyName) {
    var oSS = window.event.srcElement.SS;
    oSS.syncSelectedIndex();

    if (null != oSS.select.onchange)
      oSS.select.onchange();
  }
}
function SS_handleMousewheel (event)
{
  var idx = this.selectedIndex;
  if ('mousewheel'==event.type && this.bFocused && this.bReverse) {
    for (var i=0; i < event.wheelDelta; i += 120)
      idx--;
    for (var i=0; i > event.wheelDelta; i -= 120)
      idx++;
  }
  idx = Math.max(idx, 0);
  idx = Math.min(idx, this.options.length - 1);
  this.select.selectedIndex = idx;
}
function SS_handleOverTitle ()
{
  if (this.bExpanded)
    return;

  this.TitleTable.style.borderColor = SS_ENV.CR.BorderActive;
  this.TitleTable.cells(1).style.display = 'none';
  this.TitleTable.cells(2).style.display = 'block';
}
function SS_handleOutTitle ()
{
  this.TitleTable.style.borderColor = SS_ENV.CR.Border;
  this.TitleTable.cells(2).style.display = 'none';
  this.TitleTable.cells(1).style.display = 'block';
}
function SS_handleOverOption (idx)
{
  for (var i=0; i < this.options.length; i++) {
    if (i==idx)
      this.turnOnOption(i);
    else
      this.turnOffOption(i);
  }
}
function SS_turnOnOption (idx)
{
  this.OptionsTable.cells(idx).style.color = SS_ENV.CR.ReverseText;
  this.OptionsTable.cells(idx).style.backgroundColor = SS_ENV.CR.ReverseBackground;
}
function SS_turnOffOption (idx)
{
  this.OptionsTable.cells(idx).style.color = '';
  this.OptionsTable.cells(idx).style.backgroundColor = '';
}
function SS_adjustOptionsDiv ()
{
  if (this.bOriginalSelect) return;

  this.OptionsDiv.style.width = this.width;
  this.OptionsDiv.style.height = Math.min(this.options.length, this.ListMax) * this.OptionHeight ;
  this.OptionsWrapper.style.height = this.options.length * this.OptionHeight;
  this.OptionsDiv.style.overflowY = (this.options.length > this.ListMax) ? 'scroll' : '';

  var top = this.Table.offsetTop;
  var left = this.Table.offsetLeft;
  for (var El = this.Table.offsetParent; 'BODY'!=El.tagName && 'absolute'!=El.style.position && 'relative'!=El.style.position; El = El.offsetParent) {
    if ('TABLE' != El.tagName) {
      top += El.clientTop;
      left += El.clientLeft;
    }
    top += El.offsetTop;
    left += El.offsetLeft;
  }
  this.OptionsDiv.style.top = (this.bListDown) ? (top + this.height) : (top - parseInt(this.OptionsDiv.style.height));
  this.OptionsDiv.style.left = left;

  this.TitleWrapper.style.top = 0;
  this.TitleWrapper.style.left = 0;
}
function SS_syncOptions ()
{
  if (this.bOriginalSelect) return;

  for (var i=0; i < this.options.length; i++) {
    this.OptionsTable.cells(i).setAttribute('index', i);
    if (this.OptionsTable.cells(i).childNodes(0).innerText != this.options[i].innerText)
      this.OptionsTable.cells(i).childNodes(0).innerText = this.options[i].innerText;
  }
}
function SS_pressTitle (event)
{
  SS_cancelEvent(event);

  this.srcElementOfLastMousedown = event.srcElement;

  this.toggleOptions();
}
function SS_releaseTitle (event)
{
  SS_cancelEvent(event);

  if (this.isThisEventToBeCanceled(event)) return;

  this.srcElementOfLastMousedown = null;
}
function SS_pressOption (event)
{
  SS_cancelEvent(event);

  this.srcElementOfLastMousedown = event.srcElement;
}
function SS_moveOption (event)
{
  SS_cancelEvent(event);

  if (this.isThisEventToBeCanceled(event)) return;
  if (!(event.offsetX >= 0 && event.offsetX <= this.OptionsTable.offsetWidth)) return;

  this.handleOverOption(Math.floor(event.offsetY / this.OptionHeight));
}
function SS_releaseOption (event)
{
  SS_cancelEvent(event);

  if (this.isThisEventToBeCanceled(event)) return;

  this.srcElementOfLastMousedown = null;

  if (event.offsetX >= 0 && event.offsetX <= this.OptionsTable.offsetWidth) {
    this.toggleOptions(false);
    this.select.selectedIndex = Math.floor(event.offsetY / this.OptionHeight);
  }
}
function SS_createTable ()
{
  this.Table = SS_createElement(""
    + "<table border=0 cellpadding=0 cellspacing=0 style='table-layout:fixed; "+SS_ENV.MouseStyle+"'>"
    + "<tr><td></td></tr>"
    + "</table>"
  );
  if (!isNaN(this.width))
    this.Table.style.width = this.width;
  this.Table.style.height = this.height;

  if (!this.bOriginalSelect) {
    this.createTitleDiv();
    this.createOptionsDiv();
    this.Table.cells(0).appendChild(this.TitleDiv);
    this.Table.cells(0).appendChild(this.OptionsDiv);
  }
  else {
    this.Table.cells(0).appendChild(this.select);
  }
}
function SS_createTitleDiv ()
{
  this.TitleDiv = SS_createElement(""
    + "<div style='position:relative; top:0; left:0;'>"
    + "  <table border=0 cellpadding=0 cellspacing=0"
    + "    height="+this.height
    + "    bgcolor=FFFFFF"
    + "    style='table-layout:fixed; border:0 solid "+SS_ENV.CR.Border+";'"
    + "    onmouseover='SS_VAR.SelectList["+this.ssID+"].adjustOptionsDiv()'"
    + "  >"
    + "  <tr>"
    + "    <td><nobr style='text-oveflow:hidden;"+SS_ENV.OptionNobrStyle+"'></nobr></td>"
    + "    <td width="+SS_ENV.ButtonWidth+" align=center style='word-wrap:normal'></td>"
    + "    <td style='display:none' width="+SS_ENV.ButtonWidth+" align=center style='word-wrap:normal'></td>"
    + "    <td style='display:none'></td>"
    + "  </tr>"
    + "  </table>"
    + "</div>"
  );
  this.TitleTable = this.TitleDiv.childNodes(0);
  this.TitleTable.cells(0).childNodes(0).innerText = this.options[this.selectedIndex].innerText;
  this.TitleTable.cells(1).innerHTML = "<img src='/bbs/images/bt_arrow.gif' border=0 align=absmiddle>";
  this.TitleTable.cells(2).innerHTML = "<img src='/bbs/images/bt_arrow.gif' border=0 align=absmiddle>";
  this.TitleTable.cells(3).appendChild(this.select);
  this.TitleWrapper = document.createElement(""
    + "<img src='/bbs/images/blank.gif'"
    + "  style='position:absolute; top:0; left:0; z-index:2; width:100%; height:"+this.height+";'"
    + "  onmouseover='SS_VAR.SelectList["+this.ssID+"].handleOverTitle()'"
    + "  onmouseout='SS_VAR.SelectList["+this.ssID+"].handleOutTitle(); SS_VAR.SelectList["+this.ssID+"].releaseTitle(window.event);'"
    + "  onmousedown='SS_VAR.SelectList["+this.ssID+"].pressTitle(window.event)'"
    + "  ondblclick='SS_VAR.SelectList["+this.ssID+"].pressTitle(window.event); SS_VAR.SelectList["+this.ssID+"].releaseTitle(window.event);'"
    + "  onmouseup='SS_VAR.SelectList["+this.ssID+"].releaseTitle(window.event)'"
    + "  onmousewheel='SS_VAR.SelectList["+this.ssID+"].handleMousewheel(window.event)'"
    + "  ondragstart='SS_cancelEvent(window.event)'"
    + ">"
  );
  SS_CreatedElements[SS_CreatedElements.length] = this.TitleWrapper;
  this.TitleDiv.appendChild(this.TitleWrapper);
}
function SS_createOptionsDiv ()
{
  this.OptionsDiv = SS_createElement(""
    + "<div style='"+SS_ENV.OptionsDivStyle+"'"
    + "  onscroll='SS_VAR.SelectList["+this.ssID+"].moveOption(window.event)'"
    + "  onmousedown='SS_cancelEvent(window.event)'"
    + ">"
    + "  <table border=0 cellpadding=3 cellspacing=0 width=100% style='table-layout:fixed'>"
    + "  </table>"
    + "</div>"
  );
  this.OptionsTable = this.OptionsDiv.childNodes(0);
  for (var i=0; i < this.options.length; i++) {
    this.createOptionTr(i);
  }
  this.syncOptions();
  this.OptionsWrapper = document.createElement(""
	+ "<img src='/bbs/images/blank.gif'"
    + "  style='position:absolute; top:0; left:0; width:100%;'"
    + "  onmousedown='SS_VAR.SelectList["+this.ssID+"].pressOption(window.event)'"
    + "  onmousemove='SS_VAR.SelectList["+this.ssID+"].moveOption(window.event)'"
    + "  onmouseup='SS_VAR.SelectList["+this.ssID+"].releaseOption(window.event)'"
    + "  onmouseout='SS_VAR.SelectList["+this.ssID+"].releaseOption(window.event)'"
    + "  ondragstart='SS_cancelEvent(window.event)'"
    + ">"
  );
  SS_CreatedElements[SS_CreatedElements.length] = this.OptionsWrapper;
  this.OptionsDiv.appendChild(this.OptionsWrapper);
}
function SS_createOptionTr (idx)
{
  idx = ('undefined'!=typeof(idx)) ? idx : this.options.length - 1;
  var OptionTr = this.OptionsTable.insertRow(-1);
  var OptionTd = document.createElement("<td height="+this.OptionHeight+"></td>");
  SS_CreatedElements[SS_CreatedElements.length] = this.OptionsTd;
  OptionTd.appendChild(document.createElement("<nobr style='"+SS_ENV.OptionNobrStyle+"'></nobr>"));
  OptionTr.appendChild(OptionTd);
}



function sectionChange(form)
{
	var idx_office = SS_office.selectedIndex;
	var office_section = section_list[idx_office];
	var tmp, i, len;

	len = SS_section.options.length;

	for (i = office_section.length - 1; i >= 0; i--) {
		tmp = office_section[i].split(",");
		SS_section.insertOption(tmp[1], tmp[0], 0);
	}

	offset = office_section.length;
	for (i = office_section.length; i < office_section.length + len; i++) {
		SS_section.deleteOption(offset);
	}
}

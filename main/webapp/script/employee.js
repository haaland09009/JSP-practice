/**
 * 
 */

 function loginCheck() {
	 if(document.frm.id.value.length == 0) {
		 alert("아이디를 작성해주세요.");
		 frm.id.focus();
		 return false;
	 }
	 if(document.frm.id.value.length < 4) {
		 alert("아이디는 4글자 이상이어야 합니다.");
		 frm.id.focus();
		 return false;
	 }
	 if(document.frm.pass.value == "") {
		 alert("비밀번호를 입력해주세요.");
		 frm.pass.focus();
		 return false;
	 }
 }
 
 function joinCheck() {
	 if(document.frm.id.value.length == 0) {
		 alert("아이디를 작성해주세요.");
		 frm.id.focus();
		 return false;
	 }
	 if(document.frm.id.value.length < 4) {
		 alert("아이디는 4글자 이상이어야 합니다.");
		 frm.id.focus();
		 return false;
	 }
	 if(document.frm.pass.value == "") {
		 alert("비밀번호를 입력해주세요.");
		 frm.pass.focus();
		 return false;
	 }
	 if(document.frm.name.value.length == 0) {
		 alert("이름을 입력해주세요.");
		 frm.name.focus();
		 return false;
	 }
 }
 
 function updateCheck() {
	 if(document.frm.pass.value == "") {
		 alert("수정하고 싶은 비밀번호를 입력해주세요.");
		 frm.pass.focus();
		 return false;
	 }
 }
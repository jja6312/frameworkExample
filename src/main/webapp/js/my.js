$("#boardWriteBtn").click(async ()=>{
    const boardWriter = $("#boardWriter").val();
    const boardContent = $("#boardContent").val();
    let data = {sign:"boardWrite", boardWriter, boardContent};
    data = JSON.stringify(data);
    data = await fetch("main",{method:"post", body:data});
    data = await data.json();
    if (data.msg){
        alert(data.msg);
    }


})

$(document).on("click", "#logoutBtn", ()=>{
    let data = {sign:"logout"};
    fetch("main", {method:"post",body:data});
    $.removeCookie("name");
    document.location.href="index.html";
})


let loginedBoardWriteSpanText = `<a href="#" class="text-white" data-bs-toggle="modal" data-bs-target="#boardWriteModal">글쓰기</a>`;
const loginedName = $.cookie('name');
if (loginedName){
    $("#loginDiv").html(`${loginedName}님 환영합니다!<button id="logoutBtn">로그아웃</button>`);
    $("#boardWriterSpan").html(loginedBoardWriteSpanText);
    $("#boardWriter").val(loginedName);
}

$("#loginBtn").click(async ()=>{
    const id = $("#id").val();
    const pw = $("#pw").val();
    let data = {id, pw, "sign":"login"};
    console.log(data);
    data = JSON.stringify(data);
    console.log(data);

    data = await fetch("main", {method:"post", body:data})
    console.log(data);

    data = await data.json();
    console.log(data);

    if (data.name){
        $.cookie('name',data.name);
        $("#loginDiv").html(`${data.name}님 환영합니다!<button id="logoutBtn">로그아웃</button>`);
        $("#boardWriterSpan").html(loginedBoardWriteSpanText);
        $("#boardWriter").val(data.name);
    }else{
        alert(data.msg);
    }





})



$(document).on("click","#boardWriteForm", ()=>{
    const boardWriteFormHtml = `
    <div>
    글쓴이<input id="boardWriter2" readonly/><br>
    글내용<br></br>
    <textarea id="boardContent2"></textarea><br>
    <button id="boardWriteBtn2">글 등록</button>
</div>
    `;
    $("#mainDiv").html(boardWriteFormHtml);

    const name = $.cookie("name");
    $("#boardWriter2").val(name);
});

$(document).on("click","#boardWriteBtn2",async ()=>{
    const boardWriter = $("#boardWriter2").val();
    const boardContent = $("#boardContent2").val();
    let data = {sign:"boardWrite", boardWriter, boardContent};
    data = JSON.stringify(data);
    data = await fetch("main",{method:"post", body:data});
    data = await data.json();
    if (data.msg){
        alert(data.msg);
    }else{
        console.log(data.list);
        data = JSON.parse(data.list);
        console.log(data);

        let listText = `
        <table class="table table-striped">
        <thead>
        <tr>
        <th>글번호</th>
        <th>글쓴이</th>
        <th>제목</th>
        <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        `
        data.forEach((item,index)=>{
            listText+=`
            <tr>
            <td>${item.bno}</td>
            <td>${item.writer_id}</td>
            <td>${item.content}</td>
            <td>${item.regDate}</td>
            </tr>
            `
        })

        listText+=`</tbody></table>`;
        $("#mainDiv").html(listText);

    }


})

$(document).on("click", "#logoutBtn", ()=>{
    let data = {sign:"logout"};
    fetch("main", {method:"post",body:data});
    $.removeCookie("name");
    document.location.href="index.html";
})

let loginedBoardWriteSpanText = `<a id="boardWriteForm" href="#" class="text-white">글쓰기</a>`;

// let loginedBoardWriteSpanText = `<a href="#" class="text-white" data-bs-toggle="modal" data-bs-target="#boardWriteModal">글쓰기</a>`;
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
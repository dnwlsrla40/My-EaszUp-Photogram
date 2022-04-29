// (1) 회원정보 수정
function update(userid, event) {
    event.preventDefault();
    let data = $("#profileUpdate").serialize();
    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userid}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => {    // HttpStatus 상태코드 200번대면 여기로
        console.log("성공", res);
        location.href=`/user/${userid}`;
    }).fail(error => {  // HttpStatus 상태코드 200번대가 아니면 여기로
        if(error.data == null) {
            alert(error.responseJSON.message);
        }else {
            alert(JSON.stringify(error.responseJSON.data));
        }
    });
}
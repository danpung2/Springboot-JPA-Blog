let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{ // function(){} 대신 ()=>{} 로 사용하는 이유는 this를 바인딩 하기 위해서임
            this.save();
        });
    },
    save: function(){
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };
        // console.log(data);

        // ajax 호출 시 default가 비동기 호출
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json;charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 String인데 생긴게 json 이라면 javascript 오브젝트로 변경해줌
            // 회원가입 수행 요청
        }).done(function (response){
            console.log(response);
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    },

}

index.init();
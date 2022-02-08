let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{ // function(){} 대신 ()=>{} 로 사용하는 이유는 this를 바인딩 하기 위해서임
            this.save();
        });
    },
    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"

        }).done(function (response){
            console.log(response);
            alert("글 등록이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

}

index.init();
let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{ // function(){} 대신 ()=>{} 로 사용하는 이유는 this를 바인딩 하기 위해서임
            this.save();
        });
        $("#btn-delete").on("click", ()=>{ // function(){} 대신 ()=>{} 로 사용하는 이유는 this를 바인딩 하기 위해서임
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{ // function(){} 대신 ()=>{} 로 사용하는 이유는 this를 바인딩 하기 위해서임
            this.update();
        });
        $("#btn-reply-save").on("click", ()=>{ // function(){} 대신 ()=>{} 로 사용하는 이유는 this를 바인딩 하기 위해서임
            this.replySave();
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
            alert("글 등록이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    deleteById: function(){
        let id = $("#id").text()

        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"

        }).done(function (response){
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    update: function(){
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function (response){
            alert("글 수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    replySave: function(){
        let data = {
            content: $("#reply-content").val()
        };

        let boardId = $("#boardId").val()

        $.ajax({
            type: "POST",
            url: `/api/board/${boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function (response){
            alert("댓글 등록이 완료되었습니다.");
            location.href = `/board/${boardId}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    replyDelete: function(boardId, replyId){
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function (response){
            alert("댓글 삭제가 완료되었습니다.");
            location.href = `/board/${boardId}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

}

index.init();
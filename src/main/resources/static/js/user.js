let index = {

    init:function(){
      $("#btn-save").on("click",()=>{ // function(){},()=>{} this를 바인딩하기 위해서!
        this.save();
      });
    },

    save: function(){
      //alert("user의 save함수 호출됨");
      let data = {
          username:$("#username").val(),
          password:$("#password").val(),
          email:$("#email").val()
      };



// ajax호출시 default가 비동기 호출
//ajax 통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert 요청!!
//ajax가 통신을 성공하고 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요
      $.ajax({  // 회원가입 수행 요청
        type:"POST",
        url:"/blog/api/user",
        data:JSON.stringify(data), //JSON으로 변경해 // http body데이터
        contentType:"application/json; charset=urf-8", //body데이터가 어떤 타입인지(MIME)
        dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모드느 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경

      }).done(function(resp){  // 정상이면 이거 실행
        alert("회원가입이 완료되었습니다.");
        console.log(resp);
        location.href="/blog";
      }).fail(function(error){ // 실패하면 이거 실행
        alert(JSON.stringify(error));
      });
    }
}

index.init();

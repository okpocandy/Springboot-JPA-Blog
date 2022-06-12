let index = {

	init: function() {
		$("#btn-save").on("click", () => { // function(){},()=>{} this를 바인딩하기 위해서!
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},
	
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		// ajax호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert 요청!!
		//ajax가 통신을 성공하고 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요
		$.ajax({  // 회원가입 수행 요청
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //JSON으로 변경해 // http body데이터
			contentType: "application/json; charset=urf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모드느 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경

		}).done(function(resp) {  // 정상이면 이거 실행
			alert("글쓰기가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) { // 실패하면 이거 실행
			alert(JSON.stringify(error));
		});
	},
	
	deleteById: function() {
		let id = $("#id").text();
	
		$.ajax({  // 회원가입 수행 요청
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json" 
		}).done(function(resp) {  // 정상이면 이거 실행
			alert("삭제가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) { // 실패하면 이거 실행
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({  // 회원가입 수행 요청
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), //JSON으로 변경해 // http body데이터
			contentType: "application/json; charset=urf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모드느 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
		}).done(function(resp) {  // 정상이면 이거 실행
			alert("글수정이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) { // 실패하면 이거 실행
			alert(JSON.stringify(error));
		});
	}
}

index.init();
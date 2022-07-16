$(document).ready(function (){
    showExtensions();
});
    //체크 및 해제 시 변동될 때 마다 true, false 로 값이 변환되게 하는 함수
    function check(targetId){
        $(`#ck${targetId}`).change(function (){

            $.ajax({
                type : "PUT",
                url : `fix-extension/${targetId}`,
                success : function (response){
                    console.log(targetId+"번째 인덱스 변화감지")
                }
            })
        })
    }
    //확장자 보여주는 함수
    function showExtensions(){
    //고정확장자 보여주는 ajax
        $.ajax({
            type : "GET",
            url : "/fix-extension",
            success : function (response){
                for(let i = 0; i<response.length; i++){
                    let id = response[i].id;
                    let extension = response[i].fixExtension;
                    let isChecked = response[i].checked;
                    //html양식으로 input태그에 해당되는 id, extension 각각 대입해준다
                    let temp_html = `<span class="checkbox"><input id="ck${id}" type="checkbox"> ${extension}</span>`
                    $('#check-list').append(temp_html);

                    //체크가 돼있던 확장자는 true로 체크상태를 유지하고
                    if(isChecked === true){
                        $(`#ck${id}`).prop("checked", true);

                    }
                    //해제 돼있던 확장자는 false로 해제상태 유지한다.
                    else{
                        $(`#ck${id}`).prop("checked",false);
                    }
                    check(id);
                }
            }
        })
        //커스텀확장자 보여주는 ajax
        $.ajax({
            type : "GET",
            url : "/custom-extension",
            success : function (response){
                $('#custom-box').empty();
                //몇개 확장자가 저장됐는지 카운트
                let cnt = Object.keys(response).length;
                let cnt_html = `<div class="count-extension">${cnt}/200</div>`
                $('#custom-box').append(cnt_html);
                for(let i =0; i<response.length; i++){
                    let id = response[i].id;
                    let customExtension = response[i].customExtension;
                    let temp_html = `<div id="word-box">
                                        <span class="word-space">${customExtension}</span>
                                        <img src="images/wrong.png" onclick="deleteExtension(${id})" alt="x" width="12">
                                    </div>`
                    $('#custom-box').append(temp_html);
                }
            }
        })
    }
    //커스텀 확장자 추가하는 함수
    function save(){

        let extension=$('#custom-extension').val();
        let data={extension:extension};
        $.ajax({
            type : "POST",
            url : "/custom-extension",
            contentType : "application/json",
            data : JSON.stringify(data),
            success : function (response){
                $('#custom-box').empty();
                //몇개 확장자가 저장됐는지 카운트
                let cnt = Object.keys(response).length;
                let cnt_html = `<div class="count-extension">${cnt}/200</div>`
                $('#custom-box').append(cnt_html);
                for(let i =0; i<response.length; i++){
                    let id = response[i].id;
                    let customExtension = response[i].customExtension;
                    let temp_html = `<div id="word-box">
                                    <span class="word-space">${customExtension}</span>
                                    <img src="images/wrong.png" onclick="deleteExtension(${id})" alt="x" width="12">
                                    </div>`
                    $('#custom-box').append(temp_html);
                }
            },
            error : function (jqXHR){
            //에러 시 도출하는 메시지 텍스트를 가져온다
            alert(jqXHR.responseText);
        }
        })
    }
    //커스텀 확장자 삭제하는 함수
    function deleteExtension(targetId){
    $.ajax({
        type : "DELETE",
        url : `/custom-extension/${targetId}`,
        success : function (response){
            $('#custom-box').empty();
            //몇개 확장자가 저장됐는지 카운트
            let cnt = Object.keys(response).length;
            let cnt_html = `<div class="count-extension">${cnt}/200</div>`
            $('#custom-box').append(cnt_html);
            for(let i =0; i<response.length; i++){
                let id = response[i].id;
                let customExtension = response[i].customExtension;
                let temp_html = `<div id="word-box">
                                    <span class="word-space">${customExtension}</span>
                                    <img src="images/wrong.png" onclick="deleteExtension(${id})" alt="x" width="12">
                                </div>`
                $('#custom-box').append(temp_html);
            }
        }
    })
}

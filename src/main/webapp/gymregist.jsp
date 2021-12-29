<%@page import="com.passgym.gym.vo.Gym"%>
<%@page import="com.passgym.owner.vo.Owner"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>gymregist.jsp</title>
<style>
.regist__equiplist{
    padding-left: 0px;
    list-style-type: none;
}
.regist__equipcount1{
     display:none;
 }  
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./js/gymregist.js"></script>
<script>

    function getEquip(){
        //장비목록요청
        //$('regist__gymequip').load('./equiplist');
        //let regist__gymequip = $('.regist__gymequip'); //ul
        $.ajax({
            url: './equiplist',
            method : 'get',
            success:function(responseObj){
                console.log(responseObj);
                let list  = '';
                $(responseObj).each(function(index, element){
                    let eNo =  element.equipNo;
                    let eName = element.equipName;
                    list += '<li><input type="checkbox" class="regist__equipcheck"><span>' + eName 
                        + '</span><input type="number" class="regist__equipcount1" name="equip_no_' + eNo
                        +'" value="0"></li>';
                });
                console.log(list);
                $(".regist__equiplist").html(list);
            }
        });

        $('.regist__equiplist').on('click','li>.regist__equipcheck', function(){           
            let $countObj = $(this).siblings('.regist__equipcount1');
            console.log($countObj);
            if($(this).prop('checked')){
                $countObj.val(1);
                $countObj.show();
            }else{
                $countObj.val(0);
                $countObj.hide();
            }
        });
    }



    $(function(){
        getEquip();
        formSubmitted();
        passAddBtClick();
        passRemoveBtClick();
    })
</script>

</head>
<meta charset="UTF-8">
<body>
<%
Owner sessionOwner = (Owner)session.getAttribute("signupInfo");
Gym sessionGym = (Gym)session.getAttribute("gymInfo");
%>
세션ID : <%=session.getId() %><br>
세션ownerNo : <%=sessionOwner.getOwnerNo() %><br>
세션gymName : <%=sessionGym.getName() %><br>
<div class="regist">
        <form class="regist__form" method="post" action="./gymregist">
            <div class="regist__header">
                <img class="regist__img" src=""><br>
                <div class="regist__gymname">
                헬스장 이름 : <%=sessionGym.getName()%>
                </div>
                <div class="regist__gymaddr">
                헬스장 주소<br> 
                <%=sessionGym.getAddr()%><br>
                <%=sessionGym.getAddrDetail()%><br>
                </div>
            </div>
            <input class="regist__gymintroduce" type="text" name="introduce"><br>
            <!----->
            <input type="hidden" name="i">
            <div class="regist__gympassall" style="border: 1px solid #ccc">
                <div class="regist__gympass">
                    이용권 정보 입력<br>
                    이용권 번호 : <input class="regist__passno" type="text" name="passno0" value="0" readonly><br>
                    이용권 이름 : <input class="regist__passname" type="text" name="passname0"><br>
                    이용권 가격 : <input class="regist__passprice" type="text" name="passprice0"><br>
                    이용권 개월수 : <input class="regist__passmonth" type="text" name="passmonth0"><br>
                    일시정지 가능 횟수 : <input class="regist__pausecount" type="text" name="pausecount0"><br>
                    일시정지 가능 일수 : <input class="regist__pausedate" type="text" name="pausedate0"><br>
                    비고 : <input class="regist__remarks" type="text" name="remarks0"><br>
                    <br>
                </div> 
            </div>

            <div class="regist__passbt">
                <button class="regist__passaddbt" type="button">+</button><br>
                <button class="regist__passremovebt" type="button">-</button><br>
            </div>
            
             헬스장 정보<br>
            <input class="regist__gymnotice" type="text" name="notice"><br>

            <div class="regist__operationinfo">
                운영시간<br>
                <input class="regist__gymoperatingtime" type="text" name="operatingtime"><br>
                운영프로그램<br>
                <input class="regist__gymoperatingprogram" type="text" name="operatingprogram"><br>
                보유장비<br>
                <div class="regist__gymequipselect">
                    <div class="regist__gymequip" style="border: 1px solid #ccc">
                          기구를 선택하세요
                        <ul class="regist__equiplist">
                        </ul>  
                    </div>
                </div>
                추가서비스<br>
                <input class="regist__gymextraservice" type="text" name="extraservice"><br>
                기타<br>
                <input class="regist__etc" type="text" name="etc"><br>
            </div>

                <button type="submit" class="regist__gymsubmit">저장</button>
        </form>
    </div>

</body>
</html>
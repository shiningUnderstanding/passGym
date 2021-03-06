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
     .regist{
        width: 100%;
        height: 50vh 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
     }
    
     .regist__gymintroduce,
     .regist__gymoperatingtime,
     .regist__gymoperatingprogram,
     .regist__gymextraservice,
     .regist__gymnotice,
     .regist__etc{
        width: 312px!important;
        height: 48px;
        border: 1px solid darkslategray;
        
     }
    
    
     /* .regist__gymintroduce,
     .regist__passno,
     .regist__passname{
        display: flex;
        align-items: center;
        flex-direction: column;
        
     } */
     .regist__passbt{
        display: flex;
        flex-direction: row;
        justify-content: center;
        
     }
    
     .regist__passaddbt,
     .regist__passremovebt,
     .regist__photoregistbt1,
     .regist__photoregistbt2{
        background-color: #f6f5ef;
        border: 1px solid #f6f5ef;
        border-radius: 4px;
        font-size: 16px;
        color: darkslategray;
        cursor: pointer;
     }
    
    
    </style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./js/gymregist.js"></script>
<script>
    $(function(){
        formSubmitted();
        passAddBtClick();
        passRemoveBtClick();
        getEquip();
    });

    function getEquip(){
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
                    $(".regist__equiplist").html(list);
                }
            });

            $('.regist__equiplist').on('click','li>.regist__equipcheck', function(){           
                let $countObj = $(this).siblings('.regist__equipcount1');
                if($(this).prop('checked')){
                    $countObj.val(1);
                    $countObj.show();
                }else{
                    $countObj.val(0);
                    $countObj.hide();
                }
            });
        }

</script>
<style>
    
</style>

</head>
<meta charset="UTF-8">
<body>
<%
Owner sessionOwner = (Owner)session.getAttribute("signupInfo");
Gym sessionGym = (Gym)session.getAttribute("gymInfo");
String path = (String)session.getAttribute("photoPath");
%>
<div class="regist">
    <form method="post" class="regist__photoregist" enctype="multipart/form-data" action="./gymphotoregist">
        <input type="file" class="regist__photoregistbt1" name="gymface" accept="image/*"><br>
        <input type="submit" class="regist__photoregistbt2" value="??????????????????">
    </form>
        <form class="regist__form" method="post" action="./gymregist"> 
            <div class="regist__header">
                <img class="regist__img" src=""><br>
                
                <div class="regist__gymname">
                ????????? ?????? : <%=sessionGym.getName()%>
                </div>
                <div class="regist__gymaddr">
                ????????? ??????<br> 
                <%=sessionGym.getAddr()%><br>
                <%=sessionGym.getAddrDetail()%><br>
                </div>
            </div>
            ?????????<br>
            <textarea class="regist__gymintroduce" type="text" name="introduce"></textarea><br>
            <!----->
            <input type="hidden" name="i">
            <div class="regist__gympassall" style="border: 1px solid #ccc">
                <div class="regist__gympass">
                    ????????? ?????? ??????<br>
                    ????????? ??????<br> 
                    <input class="regist__passno" type="text" name="passno0" value="0" readonly><br>
                    ????????? ??????<br>
                    <input class="regist__passname" type="text" name="passname0"><br>
                    ????????? ??????<br>
                    <input class="regist__passprice" type="text" name="passprice0"><br>
                    ????????? ?????????<br>
                    <input class="regist__passmonth" type="text" name="passmonth0"><br>
                    ???????????? ?????? ??????<br>
                    <input class="regist__pausecount" type="text" name="pausecount0"><br>
                    ???????????? ?????? ??????<br>
                    <input class="regist__pausedate" type="text" name="pausedate0"><br>
                    ??????<br>
                    <input class="regist__remarks" type="text" name="remarks0"><br>
                    <br>
                </div> 
            </div>

            <div class="regist__passbt">
                <button class="regist__passaddbt" type="button">+</button><br>
                <button class="regist__passremovebt" type="button">-</button><br>
            </div>
            
             ????????? ??????<br>
            <textarea class="regist__gymnotice" type="text" name="notice"></textarea><br>

            <div class="regist__operationinfo">
                ????????????<br>
                <textarea class="regist__gymoperatingtime" type="text" name="operatingtime"></textarea><br>
                ??????????????????<br>
                <textarea class="regist__gymoperatingprogram" type="text" name="operatingprogram"></textarea><br>
                ????????????<br>
                <div class="regist__gymequipselect">
                    <div class="regist__gymequip" style="border: 1px solid #ccc">
                          ????????? ???????????????
                        <ul class="regist__equiplist">
                        </ul>  
                    </div>
                </div>
                ???????????????<br>
                <textarea class="regist__gymextraservice" type="text" name="extraservice"></textarea><br>
                ??????<br>
                <textarea class="regist__etc" type="text" name="etc"></textarea><br>
            </div>

                <button type="submit" class="regist__gymsubmit">??????</button>
        </form>
    </div>

</body>
</html>
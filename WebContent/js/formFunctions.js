function clearFn()
{
	document.getElementById("Uname").value="";
	document.getElementById("Passwd").value="";
	
}

function checkPwdsEqual()
{
	var oldPw = $("#pw");
	var newPw = $("#newPw");
	if(oldPw==newPw)
	{
		$(document).ready(function()//function to execute after the DOM is ready
		{
			$('#invalidPws').modal('show');
		});
	}
}

function clrReset()
{
	document.getElementById("pw").value="";
	document.getElementById("newPw").value="";
	document.getElementById("reTypPw").value="";
}

function getError()
{
	return errorMsg;
}

function setError(msg)
{
	errorMsg=msg;
}

function gotFcsUn()
{
	document.getElementById("Uname").value="";
	document.getElementById("login").disabled = true;
}

function gotFcsPw()
{
	document.getElementById("Passwd").value="";
}


function resetFn()
{
    $(".bdyReset").mouseover(function(){
    	$(".bulletin").fadeIn();
    });
  
    $("#cls").click(function(){
    	$(".bulletin").fadeOut();
    });
    
    $('#rest').click(function() {
    	var pw=document.getElementById('pw').value;
    	var newPw=document.getElementById("newPw").value;
    	var reNewPw=document.getElementById("reTypPw").value;
    	if(newPw==reNewPw)
    	{
    		if(pw!=newPw)
    		{
    			$.post('resetPwServ', {
                    PW : pw,
                    NEWPW: newPw,
                    RETYPW:reNewPw,
           					 },
           					    function(responseText) {
           						if(responseText=="InvalidPassword")
           						{       								   
           							 	$('#pwdWrong').modal('show');
           							 	$( '#clr' ).trigger( "click" );
           						}
           						else if(responseText=="Success")
           						{       								   
        							 	$('#successPwdChanged').modal('show');
        							 	$( '#clr' ).trigger( "click" );
        						}       						
            		});  
    		}
    		else
    		{
    			$('#pwsSame').modal('show');
    		 	$( '#clr' ).trigger( "click" );
    		}
    		      	
    	}
    	else
    	{
    		$('#pwdMisMatch').modal('show');
		 	$( '#clr' ).trigger( "click" );
    	}
    	
    }); 
}
    
function checkDemographics()
{
	
	 $('#forecast').click(function() {
	
	 $.post('checkDemographics',
    		 function(responseText){
						if(responseText=="success")
						{
							
							
							document.getElementById("home").src = 'Forecast.jsp';
						}

					 else if(responseText=="failure")
						{
						
						 document.getElementById("home").src = 'AddProfile.jsp';
						}
					

 });
	 
});
}

	 
function submitParameter() 
{
	
	 $('#param').click(function() {
	        
	       $.post('beforeAddParameter',
	   				function(responseText){
	    				if(responseText=="success")
	    				{
	  						document.getElementById("home").src = 'AddParameter.jsp';
	    				}
	    				else if(responseText=="failure")
	    				{
	    					
	    					document.getElementById("home").src = 'AddProfile.jsp';
	     				}
	    			} 

			);
	 });
	
}

    
function getDoctorName()
{
	
	$('#registerForm').click(function() {
	
	$.post('getDoctorNames',
   		 	function(responseText){
						if(responseText=="success")
						{
							
							
							window.location.href='Register.jsp';
						}
    	}
    	);
	});


}   


function getAppointment()
{
	
	$('#viewAppointment').click(function() {
		
		
		$.post('getSchedule', {date: "now"},
				function(responseText){
			if(responseText=="success")
			{
				
				
				document.getElementById("doctorhome").src = 'ViewAppointment.jsp';
			}
}
	   		 	
	    	);
		});

}
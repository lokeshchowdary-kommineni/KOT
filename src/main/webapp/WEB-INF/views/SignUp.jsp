            <!DOCTYPE html>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
            <html lang="en">
            <head>
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
            <meta charset="utf-8" />
            <title>KOT Ver 1.0</title>

            <meta name="description" content="User login page" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

            <!-- bootstrap & fontawesome -->
            <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
            <link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

            <!-- text fonts -->
            <link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

            <!-- ace styles -->
            <link rel="stylesheet" href="assets/css/ace.min.css" />

            <!--[if lte IE 9]>
            <link rel="stylesheet" href="assets/css/ace-part2.min.css" />
            <![endif]-->
            <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

            <!--[if lte IE 9]>
            <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
            <![endif]-->

            <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

            <!--[if lte IE 8]>
            <script src="assets/js/html5shiv.min.js"></script>
            <script src="assets/js/respond.min.js"></script>
            <![endif]-->
            </head>

            <body class="login-layout">
            <div class="main-container">
            <div class="main-content">
            <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container">
                            <div class="center">
                                    <h1>
                                           <img src="assets/images/logo-kot.png"/>
                                            <span  class="red">KOT Ver 1.0</span>
                                           
                                    </h1>
                                   
                            </div>

                            <div class="space-6"></div>

                            <div class="position-relative">
                                    <div id="login-box" class="login-box visible widget-box no-border">
                                            <div class="widget-body">
                                                    <div class="widget-main">
                                                            <h4 class="header green lighter bigger">
                                                                    <i class="ace-icon fa fa-users blue"></i>
                                                                   Sign Up
                                                            </h4>

                                                            <div class="space-6"></div>
                                                            <p> Enter your details to begin: </p>


                  <form:form action="sign_Up.html" modelAttribute="signUp" method="POST">
                          <p class="red"> ${Message}</p>
                    <fieldset>
                            <label class="block clearfix">
                                    <span class="block input-icon input-icon-right">
                                         <form:input type="text" path="firstName" class="form-control" pattern="[a-zA-Z . \s]+"  oninvalid="setCustomValidity('Please enter only Alphabets ')"  onchange="try{setCustomValidity('')}catch(e){}"  minlength="1" maxlength="50" placeholder="First Name"  required="true"/>
                                            <i class="ace-icon fa fa-user"></i>
                                    </span>
                            </label>
                            <label class="block clearfix">
                                    <span class="block input-icon input-icon-right">
                                         <form:input type="text" path="lastName" class="form-control" placeholder="Last Name" pattern="[a-zA-Z\s]+" oninvalid="setCustomValidity('Please enter only Alphabets ')" onchange="try{setCustomValidity('')}catch(e){}"  maxlength="50"/>
                                            <i class="ace-icon fa fa-user"></i>
                                    </span>
                            </label>
                            <label class="block clearfix">
                                    <span class="block input-icon input-icon-right">
                                         <form:input type="email" path="email" oninvalid="setCustomValidity('Enter a Valid Email Address')" onchange="try{setCustomValidity('')}catch(e){}" minlength="1" class="form-control" placeholder="Email" maxlength="50" required="true" />
                                            <i class="ace-icon fa fa-envelope"></i>
                                    </span>
                            </label>						

                            <label class="block clearfix">
                                    <span class="block input-icon input-icon-right">
                                         <form:input type="password" path="password" class="form-control" placeholder="Password" oninvalid="setCustomValidity('Password is Required ')" onchange="try{setCustomValidity('')}catch(e){}" minlength="1" maxlength="50" required="true" />
                                            <i class="ace-icon fa fa-lock"></i>
                                    </span>
                            </label>
                            <label class="block clearfix">
                                    <span class="block input-icon input-icon-right">
                                         <form:input type="text" path="phoneNumber" class="form-control" placeholder="Mobile" oninvalid="setCustomValidity('Please Enter 10 digit Number ')" onchange="try{setCustomValidity('')}catch(e){}"  onkeypress="return isNumberKey(event)" minlength="10" maxlength="11" required="true" />
                                            <i class="ace-icon fa fa-mobile-phone"></i>
                                    </span>
                            </label>

                           

                            <div class="space-24"></div>

                            <div class="clearfix">
                                    <button type="reset" class="width-30 pull-left btn btn-sm">
                                            <i class="ace-icon fa fa-refresh"></i>
                                            <span class="bigger-110">Reset</span>
                                    </button>										
                                 <input type="submit" class="width-35 pull-right btn btn-sm btn-primary bigger-110" value="Submit"/>

                            </div>
                    </fieldset>
            </form:form>
                                                    </div>
        </div><!-- /.widget-body -->
                                    </div><!-- /.login-box -->

                            </div><!-- /.position-relative -->

                          
                    </div>
            </div><!-- /.col -->
            </div><!-- /.row -->
            </div><!-- /.main-content -->
            </div><!-- /.main-container -->

            <!-- basic scripts -->

            <!--[if !IE]> -->
            <script src="assets/js/jquery-2.1.4.min.js"></script>

            <!-- <![endif]-->

            <!--[if IE]>
            <script src="assets/js/jquery-1.11.3.min.js"></script>
            <![endif]-->
            <script type="text/javascript">
            if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
            </script>
             <script type="text/javascript">
        function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }
      </script>

            <!-- inline scripts related to this page -->
            <script type="text/javascript">
            jQuery(function($) {
            $(document).on('click', '.toolbar a[data-target]', function(e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
            });
            });



            //you don't need this, just used for changing background
            jQuery(function($) {
            $('#btn-login-dark').on('click', function(e) {
            $('body').attr('class', 'login-layout');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
            });
            $('#btn-login-light').on('click', function(e) {
            $('body').attr('class', 'login-layout light-login');
            $('#id-text2').attr('class', 'grey');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
            });
            $('#btn-login-blur').on('click', function(e) {
            $('body').attr('class', 'login-layout blur-login');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'light-blue');

            e.preventDefault();
            });

            });
            </script>
            </body>
            </html>

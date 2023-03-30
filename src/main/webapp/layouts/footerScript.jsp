
<script src="public/assets/js/jquery.min.js"></script>
<script src="public/assets/js/bootstrap.bundle.min.js"></script>
<script src="public/assets/js/modernizr.min.js"></script>
<script src="public/assets/js/jquery.slimscroll.js"></script>
<script src="public/assets/js/waves.js"></script>
<script src="public/assets/js/jquery.nicescroll.js"></script>
<script src="public/assets/js/jquery.scrollTo.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<script src="public/plugins/bootstrap-filestyle/js/bootstrap-filestyle.min.js"></script>
<script src="public/plugins/select2/js/select2.min.js"></script>
<script src="public/plugins/parsleyjs/parsley.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/locales/bootstrap-datepicker.es.min.js" charset="UTF-8"></script>

<script>
        function filterFloat(evt,input){
                // Backspace = 8, Enter = 13, ‘0′ = 48, ‘9′ = 57, ‘.’ = 46, ‘-’ = 43
                var key = window.Event ? evt.which : evt.keyCode;
                var chark = String.fromCharCode(key);
                var tempValue = input.value+chark;
                if(key >= 48 && key <= 57){
                        if(filter(tempValue)=== false){
                                return false;
                        }else{
                                return true;
                        }
                }else{
                        if(key == 8 || key == 13 || key == 0) {
                                return true;
                        }else if(key == 46){
                                if(filter(tempValue)=== false){
                                        return false;
                                }else{
                                        return true;
                                }
                        }else{
                                return false;
                        }
                }
        }
        function filter(__val__){
                var preg = /^([0-9]+\.?[0-9]{0,2})$/;
                if(preg.test(__val__) === true){
                        return true;
                }else{
                        return false;
                }

        }
</script>

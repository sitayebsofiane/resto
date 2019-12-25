/**
 * pour le menu de l'application source bootsnif
 */
 $(document).ready(function() {
      $(".menu").click(function() {
        $("ul").slideToggle().toggleClass('active');    
      });
    });
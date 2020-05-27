<#import "spring.ftl" as spring />

<#macro content>
</#macro>

<#macro title></#macro>

<#macro main>
    <html>
    <head>
        <meta charset="utf-8">
        <meta name="view-port">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    </head>
    <body>
    <script>
        function changeLanguage() {
            var l = '<@spring.message "header.page.language"/>'.toLowerCase();
            window.history.replaceState({}, "", window.location.protocol + "//" + window.location.host + window.location.pathname +  "?lang=" + l);
        }
    </script>

    <nav>
        <div>
            <button type="button" >
                <span></span>
            </button>
            <div>
                <ul>
                    <li>
                        <a href="/logout"><@spring.message "link.logout"/></a>
                    </li>
                    <li>
                        <a href="/signUp"> <@spring.message "link.signUp"/></a>
                    </li>
                    <li>
                        <a href="/signIn"><@spring.message "link.login"/></a>
                    </li>
                    <li>
                        <a href="/profile"><@spring.message "link.profile"/></a>
                    </li>
                    <li>
                        <a  onclick="changeLanguage()" href=""><@spring.message "header.page.language"/></a>
                    </li>

                </ul>
            </div>

        </div>
    </nav>
         <@content/>
    </body>
    </html>
</#macro>
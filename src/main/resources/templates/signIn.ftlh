<#include "base.ftlh">


<#macro content>

    <form action="/signIn" method="post">

            <div align="center">
                <div>
                    <span><h1><@spring.message "page.signIn.label.login"/></h1></span>
                    <div>
                        <label for="exampleInputEmail1"><@spring.message "page.email"/></label>
                        <input type="text" placeholder="<@spring.message "page.placeholder.email"/>" name="email">
                    </div>
                    <div>
                        <label for="exampleInputPassword1"><@spring.message "page.password"/></label>
                        <input type="password"
                               placeholder="<@spring.message "page.placeholder.password"/>"
                               name="password">
                    </div>
                    <div>
                        <input type="checkbox" name="remember-me">
                        <label class="form-check-label" for="exampleCheck1"><@spring.message "page.remember"/></label>
                    </div>
                </div>
                <input type="submit" value="<@spring.message 'page.login.button.submit'/>"/>
                <a href="/signUp" class="already"><@spring.message "signUp.label.registration"/></a>
            </div>
    </form>
</#macro>

<@main/>
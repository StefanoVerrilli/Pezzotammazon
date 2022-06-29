<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Classes.Cart.CartModel - Pezzotammazon"/>
  </jsp:include>

  <jsp:include page="/Navbar.jsp">
    <jsp:param name="access_type" value="${access_type}"/>
  </jsp:include>
<body>
<div class="container">
<form method="post" action="PaymentLogic.do">

  <div class="field">
    <label class="label" for="address">Recipient name</label>
    <div class="control">
      <input class="input" type="text" name="recipient_name" id="recipient-name" placeholder="John Smith" required autocomplete="name">
    </div>
  </div>

  <div class="field">
    <label class="label" for="address">Street</label>
    <div class="control">
      <input class="input" type="text" name="address" id="address" placeholder="Main Street" required autocomplete="street-address">
    </div>
  </div>

  <div class="columns">
    <div class="column">
      <div class="field">
        <label class="label" for="postal">Postal Code</label>
        <div class="control">
          <input class="input" min="0" max="99999" type="number" name="postal" id="postal" placeholder="05487"  required autocomplete="postal-code">
        </div>
      </div>
    </div>

  <div class="column">
    <div class="field">
    <label class="label" for="city">City</label>
    <div class="control">
      <input class="input" type="text" name="city" id="city" required placeholder="New York">
    </div>
  </div>
  </div>
  </div>

  <fieldset name="payment_type">
    <div class="card">
      <footer class="card-footer">
        <input type="radio" id="card" name="payment_type" value="creditcard" class="is-hidden" onchange="getPaymentType(this)" checked>
        <label for="card" class="card-footer-item"><i class="is-size-2 fa-solid fa-credit-card"></i><p>Credit Card</p></label>
        <input type="radio" id="bancomat" name="payment_type" value="bancomat" class="is-hidden" onchange="getPaymentType(this)">
        <label for="bancomat" class="card-footer-item"><i class="fa-solid fa-building-columns is-size-2"></i><p>Bancomat</p></label>
      </footer>
    </div>
  </fieldset>

  <div id="card-details" class="box mt-5 is-hidden">
    <div class="field">
      <label class="label" for="card-owner">Card Owner</label>
      <div class="control">
        <input class="input" type="text" name="card_owner" id="card-owner" placeholder="John Smith" autocomplete="cc-family-name">
      </div>
    </div>

    <div class="field">
      <label class="label" for="card-number">Card Number</label>
      <div class="control">
        <input class="input" type="number" min="1111111111111111" max="9999999999999999" name="card_number" id="card-number" placeholder="0000 0000 0000 0000" autocomplete="cc-number">
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <div class="columns">
          <div class="column">
        <div class="field">
          <label class="label" for="card-month">Month</label>
          <div class="control">
            <input class="input" min="0" max="12" type="number" name="card_month" id="card-month" placeholder="12" autocomplete="cc-exp-month">
          </div>
        </div>
      </div>

          <div class="column">
            <div class="field">
              <label class="label" for="card-year">Year</label>
              <div class="control">
                <input class="input" min="2021" max="9999" type="number" name="card_year" id="card-year" placeholder="2022" autocomplete="cc-exp-year">
              </div>
            </div>
          </div>

        </div>
      </div>

      <div class="column">
        <div class="field">
          <label class="label" for="cvc">CVC</label>
          <div class="control">
            <input class="input" min="0" max="999" type="number" name="cvc" id="cvc" placeholder="000">
          </div>
        </div>
      </div>
    </div>
  </div>


  <div id="cc-details" class="box mt-5 is-hidden">
    <div class="field">
      <label class="label" for="cc-owner">Account Owner</label>
      <div class="control">
        <input class="input" type="text" name="cc_owner" id="cc-owner" placeholder="John Smith">
      </div>
    </div>

    <div class="field">
      <label class="label" for="cc-number">IBAN</label>
      <div class="control">
        <input class="input" type="text" name="cc_number" id="cc-number" placeholder="IT60X0542811101000000123456">
      </div>
    </div>
  </div>

  <input type="submit" class="button is-warning is-fullwidth mt-3 is-large" value="Pay and continue">
</form>

  <c:if test="${invalid_payment !=null}">
    <p class="help is-danger"><c:out value="${invalid_payment}"></c:out></p>
  </c:if>

</div>

<style>
  input[type="radio"]:checked+label {
    background: #E3FAFB;
    border: 1px solid #73a9ff;
  }

  input::-webkit-outer-spin-button,
  input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }

  input[type=number]{
    -moz-appearance: textfield;
  }
</style>


<script>

  function getSelectedType() {
    let payments = document.getElementsByName('payment_type');

    for (let i=0, len=payments.length; i<len; ++i)
      if (payments[i].checked)
        return payments[i];
  }

  window.onload =  function() {
    getPaymentType(getSelectedType());
  }



  function getPaymentType(type) {
    if(type.value==='creditcard') {
      document.getElementById('card-details').classList.remove('is-hidden');
      document.getElementById('cc-details').classList.add('is-hidden');
    } else {
      document.getElementById('cc-details').classList.remove('is-hidden');
      document.getElementById('card-details').classList.add('is-hidden');
    }
  }
</script>

</body>
</html>

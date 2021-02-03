Feature: Test API Balance_Movements

  Background:
    * url 'http://localhost:8081/balance/movements'
    * def var_body = {"data": [{"account": {"type": "CUENTA_DE_AHORRO","number": "40678098765"},"transaction": {"startDate": "2020-01-01","endDate": "'2020-01-30","minAmount": "2","maxAmount": "4","type": "DEBITO","checkNumber": "","group": "","description": ""},"pagination": {"size": "30","key": "1"},"office": {"code": "2005678","name": "2005678"}}]}

  Scenario: Consulta exitosa balance-movement
    Given headers {Content-Type: 'application/json', Channel:'APP',  Date:'2020/07/24', DateTime:'20:18:15', Ip:'127.0.0.1',ClientID:'12345678', ClientType:'CC'}
    When request var_body
    When method post
    Then status 200
    And match $.data[0].account ==  {type: "CUENTA_DE_AHORRO",number: "40678098765"}


  Scenario: Consulta fallida balance-movement sin body
    Given headers {Content-Type: 'application/json', Channel:'APP',  Date:'2020/07/24', DateTime:'20:18:15', Ip:'127.0.0.1',ClientID:'12345678', ClientType:'CC'}
    When request {}
    When method post
    Then status 500

  Scenario: Consultar balance-movements sin el header Channel
    Given headers { Content-Type: 'application/json', Date:'2020/11/05', DateTime:'10:37:15', Ip:'127.0.0.1' ,ClientID:'12345678' ,ClientType:'CC'}
    When request var_body
    And method post
    Then status 400
    And match $ == {code:"Error", message: "La cabecera 'Channel' Es requerida."}


  Scenario: Consultar balance-movements sin el header Date
    Given headers { Content-Type: 'application/json',  Channel:'APP', DateTime:'10:37:15', Ip:'127.0.0.1', ClientID:'12345678', ClientType:'CC' }
    When request var_body
    And method post
    Then status 400
    And match $ == {code:"Error", message: "La cabecera 'Date' Es requerida."}

  Scenario: Consultar balance-movements sin el header DateTime
    Given headers { Content-Type: 'application/json',Channel:'APP', Date:'2020/11/05', Ip:'127.0.0.1' ,ClientID:'12345678', ClientType:'CC' }
    When request var_body
    And method post
    Then status 400
    And match $ == {code:"Error", message: "La cabecera 'DateTime' Es requerida."}


  Scenario: Consultar balance-movements sin el header Ip
    Given headers { Content-Type: 'application/json', Channel:'APP', Date:'2020/11/05', DateTime:'10:37:15', ClientID:'12345678', ClientType:'CC' }
    When request var_body
    And method post
    Then status 400
    And match $ == {code:"Error", message: "La cabecera 'Ip' Es requerida."}

  Scenario: Consultar balance-movements sin el ClientID
    Given headers {Content-Type: 'application/json', Channel:'APP',  Date:'2020/07/24', DateTime:'20:18:15', Ip:'127.0.0.1', ClientType:'CC'}
    When request var_body
    And method post
    Then status 400
    And match $ == {code:"Error", message: "La cabecera 'ClientID' Es requerida."}

  Scenario: Consultar balance-movements sin el ClientType
    Given headers {Content-Type: 'application/json', Channel:'APP',  Date:'2020/07/24', DateTime:'20:18:15', Ip:'127.0.0.1',ClientID:'12345678'}
    When request var_body
    And method post
    Then status 400
    And match $ == {code:"Error", message: "La cabecera 'ClientType' Es requerida."}



# 1. História de Usuário

A Tabela 3 a seguir contém as Histórias de Usuárias elicitadas. 

<table>
    <thead>
        <tr style="background-color: purple; color: white" >
            <th style="border-style:solid;border-width:1px;text-align:center">ID</th>
            <th style="border-style:solid;border-width:1px;text-align:center">História de Usuário</th>
            <th style="border-style:solid;border-width:1px;text-align:center">Critérios de aceitação</th>
            <th style="border-style:solid;border-width:1px;text-align:center">Prioridade</th>
            <th style="border-style:solid;border-width:1px;text-align:center">RF/RNF relacionado</th>
            <th style="border-style:solid;border-width:1px;text-align:center">Story Points</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US01</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero poder monitorar os dados do carro para analisa-los.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>Ao entrar no aplicativo vários gráficos com diversas informações diferentes devem aparecer</li><li> Os gráficos podem ficar ocultos por opção do usuários</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">8</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US02</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero que o sistema da interface receba os dados vindos da rede LoRa por uma porta serial, para ter acesso aos dados.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>Os dados recebidos pela porta serial devem ser exibidos na interface em tempo real (sem necessidade de reiniciar o sistema).</li><li> Se a porta serial for desconectada, o sistema deve informar ao usuário com uma mensagem de erro clara.</li><li> O sistema não deve travar caso receba dados inválidos ou corrompidos (deve ignorar e continuar recebendo).</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle"> Alta </td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF10</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">13</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US03</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero que o sistema da interface trate os dados recebidos, para que seja possível transformar esses dados em informação</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>Os dados brutos devem ser convertidos em valores legíveis</li><li>O sistema deve validar os dados recebidos, descartando pacotes incompletos ou inválidos.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF04</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">3</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US04</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero que o sistema da interface armazene os dados de maneira temporária, para facilitar a implementação</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>O sistema deve manter os dados recebidos em memória enquanto a sessão da aplicação estiver ativa.</li><li>Ao encerrar ou reiniciar a aplicação, os dados armazenados temporariamente devem ser descartados.</li><li>Deve existir um mecanismo de limpeza automática caso o limite máximo de registros seja atingido.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF05</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">3</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US05</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero que o sistema da interface exiba instantaneamente os dados recebidos, para ter um monitoramento em tempo real</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>A interface deve exibir os dados em ordem cronológica de chegada.</li><li>O sistema deve suportar fluxos contínuos de dados sem travamentos ou atrasos perceptíveis.</li><li>Caso a conexão com a fonte de dados (rede LoRa/serial) seja perdida, a interface deve informar ao usuário que o monitoramento em tempo real foi interrompido.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF06</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">3</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US06</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero que o sistema exiba um gráfico com a temperatura de cada pneu, para observar a evolução da temperatura de cada pneu</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>O sistema deve exibir um gráfico de linha (ou outro adequado) mostrando a variação da temperatura de cada pneu ao longo do tempo.</li><li>Cada pneu deve estar identificado de forma única.</li></li><li>O usuário deve conseguir distinguir cada pneu por cores ou legendas diferentes.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF07</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">5</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US07</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero que o sistema da interface ofereça a opção de visualizar a temperatura individual de cada pneu, para acompanhar individualmente a temperatura</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>O usuário deve poder selecionar um pneu específico</li><li>Ao selecionar o pneu, o sistema deve exibir somente os dados de temperatura daquele pneu, em tempo real.</li><li>Deve ser possível retornar facilmente à visualização de todos os pneus.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Média</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF08</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">3</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US08</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero que o sistema da interface ofereça a opção de salvar os dados e gráficos coletados em um arquivo em formato PDF ou parecido, para comparar com dados de outras medições</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>O sistema deve permitir ao usuário exportar os dados coletados e os gráficos gerados em um arquivo PDF (ou formato equivalente, como CSV para dados brutos).</li><li>O usuário deve poder escolher o local onde salvar o arquivo.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Média</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF09</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">5</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US09</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como um usuário, eu quero que o sistema ofereça a opção de visualizar a temperatura dos pneus em um intervalo de tempo personalizado, para poder ver a temperatura em determinados pontos da pista</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>O usuário deve poder selecionar um intervalo de tempo</li><li>O gráfico deve atualizar automaticamente para exibir apenas os dados do intervalo escolhido.</li><li>O sistema deve mostrar a média, máxima e mínima da temperatura nesse intervalo.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Média</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF10</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">8</td>
        </tr>
</table>

<div style="text-align: center">
<p>Tabela 3: História de Usuário</p>
</div>

# 2. Referências


<a href="../README.md">VOLTAR INÍCIO</a>

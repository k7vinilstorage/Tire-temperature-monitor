# 1. Requisitos Funcionais

<p align="justify">A <i>Tabela 1</i> a seguir contém os Requisitos Funcionais (RF) elicitados utizando a técnica de Brainstorm.</p>

| ID   |                                 Requisito                                 | Prioridade | Requisitos Relacionados |
| :--: | :-----------------------------------------------------------------------: | :--------: | :---------: |
| RF01 | O sistema do veiculo deve ler os dados vindos dos sensores em celsius | Alta | |
| RF02 | O sistema do veiculo deve enviar os dados dos sensores para telemetria via rede CAN | Alta | RF01 |
| RF03 | O sistema da interface deve receber os dados vindo via LoRa pela porta serial | Alta | |
| RF04 | O sistema da interface deve tratar os dados recebidos | Alta|RF03 |
| RF05 | O sistema da interface deve armazenar os dados de forma temporaria | Alta | RF04|
| RF06 | O sistema da interface deve exibir instantâneamente os dados recebidos | Alta | RF04 |
| RF07 | O sistema da interface deve exibir em um grafico a temperatura de cada pneu | Alta | RF05 |
| RF08 | O sistema da intertace deve oferecer ao usuario a opção de visualizar a temperatura no grafico de cada pneu individualmente | Medio | RF07 |
| RF09 | O sistema deve oferecer a opção de salvar os dados e gráficos em um arquivo em formato PDF ou parecido | Medio | RF04|
| RF10 | O sistema deve oferecer ao usuários a opção de visualizar a temperatura dos pneus em um intervalo de tempo personalizado | Medio | RF07 |



<div style="text-align: center">
<p>Tabela 1: Requisitos Funcionais</p>
</div>

# 2. Referências


<a href="../README.md">VOLTAR INÍCIO</a>

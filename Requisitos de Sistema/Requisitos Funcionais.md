# 1. Requisitos Funcionais

<p align="justify">A <i>Tabela 1</i> a seguir contém os Requisitos Funcionais (RF) elicitados utizando a técnica de Brainstorm.</p>

| ID   |                                 Requisito                                 | Prioridade | Requisitos Relacionados |
| :--: | :-----------------------------------------------------------------------: | :--------: | :---------: |
| RF01 | O sistema do veiculo deve ler os dados vindos dos sensores em celsius | Alta | |
| RF02 | O sistema do veiculo deve enviar os dados via rede CAN | Alta | RF01 |
| RF03 | O sistema da interface deve receber os dados vindo via lora pela porta serial | Alta | |
| RF04 | O sistema da inteface deve tratar os dados recebidos | Alta| |
| RF05 | O sitema da interface deve armazenar os dados de forma temporaria | Alta |

| RF04 | O sistema deve guardar os dados em um histórico para comparações. Memória reseta a cada vez que software é iniciado |            |             |
| RF05 | O sistema deve ser capaz de realizar conversões entre outras unidades de medidas (Kelvin para Graus etc) |            |             |

| RF07 | O sistema deve oferecer a opção de salvar os dados e gráficos em um arquivo em formato PDF ou parecido |            |             |
| RF08 | O sistema deve oferecer ao usuários a opção de visualizar a temperatura dos pneus em um intervalo de tempo personalizado |            |             |


<div style="text-align: center">
<p>Tabela 1: Requisitos Funcionais</p>
</div>

# 2. Referências


<a href="../README.md">VOLTAR INÍCIO</a>

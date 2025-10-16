#include <Adafruit_MLX90614.h>
#include "driver/twai.h"
#include <Wire.h>

int can_tx = 18;
int can_rx = 19;

Adafruit_MLX90614 mlx = Adafruit_MLX90614();

void canBegin() {
  const twai_general_config_t g = {
    .mode          = TWAI_MODE_NORMAL,
    .tx_io         = (gpio_num_t)can_tx,
    .rx_io         = (gpio_num_t)can_rx,
    .clkout_io     = TWAI_IO_UNUSED,
    .bus_off_io    = TWAI_IO_UNUSED,
    .tx_queue_len  = 0,
    .rx_queue_len  = 64,
    .alerts_enabled = TWAI_ALERT_NONE,
    .clkout_divider = 0
  };
  const twai_timing_config_t t = TWAI_TIMING_CONFIG_1MBITS();
  const twai_filter_config_t f = TWAI_FILTER_CONFIG_ACCEPT_ALL();
  ESP_ERROR_CHECK(twai_driver_install(&g, &t, &f));
  ESP_ERROR_CHECK(twai_start());
}

void sendCan(uint32_t id, const uint8_t* data, uint8_t length) {
    twai_message_t msg = {};
    msg.identifier = id;
    msg.extd = 1;
    msg.rtr = 0;
    msg.data_length_code = length;
    memcpy(msg.data, data, length);

    twai_transmit(&msg, pdMS_TO_TICKS(100));
}

void sendTemp(int temp) {
  uint16_t value = temp;
  uint8_t msb = (value >> 8);
  uint8_t lsb = value;
  const uint8_t tempPayload[2] = {msb, lsb};
  sendCan(0x2013, tempPayload, 2);
}

void setup() {
  Serial.begin(115200);
  Wire.begin(16, 17);
  canBegin();

  if (!mlx.begin()) {
    Serial.println("Error connecting to MLX sensor. Check wiring.");
    while (1);
  };
}

void loop() {
  //Serial.print("Object = "); Serial.print(mlx.readObjectTempC()); Serial.println("*C");
  sendTemp(mlx.readObjectTempC() * 100);
  delay(100);
}

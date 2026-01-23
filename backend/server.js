require('dotenv').config(); // ✅ LOAD ENV FIRST

const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const twilio = require('twilio');

const app = express();

app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// 🔐 Read from ENV (NOT hardcoded)
const accountSid = process.env.TWILIO_ACCOUNT_SID;
const authToken = process.env.TWILIO_AUTH_TOKEN;

const client = twilio(accountSid, authToken);

const whatsappFrom = process.env.TWILIO_WHATSAPP_FROM;
const whatsappTo = process.env.TWILIO_WHATSAPP_TO;

// ✅ Debug check (IMPORTANT)
console.log('FROM:', whatsappFrom);
console.log('TO:', whatsappTo);

app.post('/book-service', async (req, res) => {
  const { name, phone, service, address } = req.body;

  if (!name || !phone || !service || !address) {
    return res.status(400).json({ success: false, msg: 'Missing fields' });
  }

  const message = `
📌 New Service Booking
👤 Name: ${name}
📞 Phone: ${phone}
🛠 Service: ${service}
🏠 Address: ${address}
  `;

  try {
    await client.messages.create({
      from: whatsappFrom,
      to: whatsappTo,
      body: message
    });

    res.json({ success: true, msg: 'Booking sent successfully!' });
  } catch (error) {
    console.error('Twilio Error:', error.message);
    res.status(500).json({ success: false, msg: 'Failed to send booking.' });
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`✅ Server running on port ${PORT}`);
});

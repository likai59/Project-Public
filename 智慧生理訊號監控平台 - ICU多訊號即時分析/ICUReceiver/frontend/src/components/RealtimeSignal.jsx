import React, { useEffect, useState, useRef } from 'react';

export default function RealtimeICU() {
  const [data, setData] = useState(null);
  const canvasRef = useRef(null);

  useEffect(() => {
    const ws = new WebSocket('ws://localhost:8080/ws/dynamic'); // 請替換你的port
    ws.onmessage = (event) => {
      try {
        const obj = JSON.parse(event.data);
        setData(obj);
      } catch (e) {
        console.error('資料格式錯誤:', e);
      }
    };
    return () => ws.close();
  }, []);

  useEffect(() => {
    if (!data?.ecg || !Array.isArray(data.ecg) || data.ecg.length === 0) return;
    const canvas = canvasRef.current;
    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.strokeStyle = '#42e1e1';
    ctx.beginPath();
    const { ecg } = data;
    const h = canvas.height;
    const w = canvas.width;
    const mid = h / 2;
    ecg.forEach((val, i) => {
      const x = (i / ecg.length) * w;
      const y = mid - val * (h / 3);
      i === 0 ? ctx.moveTo(x, y) : ctx.lineTo(x, y);
    });
    ctx.stroke();
  }, [data]);

  return (
    <div style={{ fontFamily: 'monospace', padding: 20, background: '#20232a', color: '#fff', borderRadius: 8, width: 440 }}>
      <h2 style={{ color: '#67f' }}>ICU 生理訊號即時監控</h2>
      {data ? (
        <>
          <div><b>National ID：</b>{data.nationalId}</div>
          <div><b>心率 (Heart Rate)：</b>{data.heartRate} bpm</div>
          <div><b>脈搏 (Pulse)：</b>{data.pulse}</div>
          <div><b>時間 (Timestamp)：</b>{data.timestamp}</div>
          <div style={{ margin: '10px 0' }}>
            <b>ECG（心電圖波形）</b>
            <canvas
              ref={canvasRef}
              width={400}
              height={80}
              style={{ display: 'block', background: '#111', border: '1px solid #444', marginTop: 4 }}
            />
          </div>
          <div>
            <b>原始 ECG 陣列:</b>
            <div style={{ fontSize: '0.92em', margin: '4px 0', maxHeight: 60, overflow: 'auto', background: '#222', padding: 2, borderRadius: 3 }}>
              {data.ecg.map((v, i) => <span key={i}>{v.toFixed(2)} </span>)}
            </div>
          </div>
        </>
      ) : <div>尚未收到資料…</div>}
    </div>
  );
}

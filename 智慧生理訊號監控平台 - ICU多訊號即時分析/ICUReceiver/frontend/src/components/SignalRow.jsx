import React, { useRef, useEffect } from "react";

export default function SignalRow({ label, value, unit, waveform, color }) {
  const canvasRef = useRef(null);

  useEffect(() => {
    if (!Array.isArray(waveform) || waveform.length === 0) return;
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");

    // 清空畫布
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // 繪製波形
    ctx.strokeStyle = color || "lime";
    ctx.beginPath();

    const height = canvas.height;
    const width = canvas.width;
    const mid = height / 2;

    waveform.forEach((v, i) => {
      const x = (i / waveform.length) * width;
      const y = mid - v * (height / 3);
      i === 0 ? ctx.moveTo(x, y) : ctx.lineTo(x, y);
    });

    ctx.stroke();
  }, [waveform, color]);

  return (
    <div style={{ marginBottom: "1rem" }}>
      <div>
        <strong style={{ color }}>{label}:</strong> {value} {unit}
      </div>
      <div
        style={{
          height: "60px",
          background: "#111",
          border: "1px solid #333",
          marginTop: "4px",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        {Array.isArray(waveform) && waveform.length > 0 ? (
          <canvas
            ref={canvasRef}
            width={300}
            height={60}
            style={{ display: "block" }}
          />
        ) : (
          <div style={{ color: "#555" }}>No signal</div>
        )}
      </div>
    </div>
  );
}

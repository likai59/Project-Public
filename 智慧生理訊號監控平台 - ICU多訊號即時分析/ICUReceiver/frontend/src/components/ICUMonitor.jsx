import SignalRow from "./SignalRow";

export default function ICUMonitor({ data }) {
  return (

    <div style={{ background: "#000", color: "#fff", padding: "1rem", fontFamily: "monospace" }}>
      <SignalRow
        label="HR"
        value={data.heartRate}
        unit="bpm"
        waveform={data.ecg}
        color="lime"
      />
    </div>
  );
}
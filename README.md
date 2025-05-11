# 📊 Benchmarking & Performance Testing Guide

> This project showcases an example Java web application (EAR-based) benchmarked using Apache HTTP Server’s `ab`, VisualVM, and Apache JMeter. Developers will learn how to set up the environment, execute load tests, and collect performance metrics.

---

## 📝 Overview

While the application is packaged as an EAR (Enterprise Archive) with standard modules (Core, EJB, Web), the primary focus here is **benchmarking** request performance under different tools. You'll learn:

1. How to download and install required software 🛠️
2. Where to unpack project files 📂
3. Load testing with **Apache `ab`** 🚀
4. Profiling with **VisualVM** 🔍
5. Advanced load testing with **Apache JMeter** 🎛️
6. Generating & analyzing JMeter results 📈

---

## 1. Download & Install Software

| Tool                          | Download Link                                                                                   | Notes                               |
| ----------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------- |
| Java JDK 11+                  | [https://adoptium.net/](https://adoptium.net/)                                                  | Install and set `JAVA_HOME`.        |
| Apache HTTP Server (Apache24) | [https://www.apachelounge.com/download/](https://www.apachelounge.com/download/)                | Needed for `ab` (Apache Bench).     |
| VisualVM                      | [https://visualvm.github.io/download.html](https://visualvm.github.io/download.html)            | For JVM profiling and monitoring.   |
| Apache JMeter                 | [https://jmeter.apache.org/download\_jmeter.cgi](https://jmeter.apache.org/download_jmeter.cgi) | Extract and configure `.jmx` tests. |

**Steps:**

1. Download the ZIP or installer for each.
2. Follow on-screen instructions or extract to a folder of your choice.
3. Ensure executables (`java`, `ab`, `visualvm`, `jmeter`) are on your `PATH`.

---

## 2. Project Setup & Extraction

1. **Clone or download** this repository:

   ```bash
   git clone https://github.com/Tharindu714/Perfomance-Testing-Guide-Project.git
   ```
2. **Extract** (if ZIP) to:

   ```text
   C:\Projects\BenchmarkingExample
   ```
3. **Deploy** the EAR to your application server (e.g., GlassFish, Payara, WildFly):

   ```bash
   asadmin deploy C:/Projects/BenchmarkingExample/ear/BenchmarkApp.ear
   ```
4. **Verify** the web module is running (e.g., `http://localhost:8080/app`).

---

## 3. Load Testing with Apache Bench (`ab`)

Apache Bench is a simple CLI tool for HTTP request benchmarking.

```bash
# Single concurrency test:
ab -n 1000 -c 10 http://localhost:8080/app/api/hello
```

* `-n 1000`: Total requests.
* `-c 10`: Concurrent clients.

```bash
# High-load test with custom header:
ab -n 5000 -c 50 -H "Authorization: Bearer token123" http://localhost:8080/app/api/data
```

**Typical Output Fields:**

* **Requests per second**: Throughput.
* **Time per request**: Latency.
* **Transfer rate**: Bandwidth.

**Save results:**

```bash
ab -n 10000 -c 100 http://localhost:8080/app/api/hello > ab_results.txt
```

---

## 4. JVM Profiling with VisualVM

VisualVM helps visualize heap, threads, and CPU usage.
![4](https://github.com/user-attachments/assets/b551ba83-e4fb-4565-adc4-8b15de86fc21)

1. **Launch VisualVM**:

   ```bash
   visualvm
   ```
2. In **Applications** pane, locate your JVM (e.g., `GlassFish Server`).
3. **Monitor**:

   * **CPU**: Real-time CPU load.
   * **Monitor**: Heap usage and GC activity.
   * **Threads**: Thread counts and states.
4. **Profile**:

   * Switch to **Sampler** or **Profiler**.
   * Start a **CPU** or **Memory** profiling session.
5. **Run load tests** concurrently (e.g., using `ab` or JMeter) to see live metrics.

> 📋 **Tip:** Take snapshots before and after tests to compare.

---

## 5. Advanced Load Testing with Apache JMeter

1. **Setup JMeter Test Plan**
![5](https://github.com/user-attachments/assets/09ffcca7-921e-42de-bd74-8a3b82121c14)

1. **Analyze Text Log**
![6](https://github.com/user-attachments/assets/076ee6f7-d078-43fc-900e-3aa06141ffb9)

1. **Result Tree View**
![7](https://github.com/user-attachments/assets/ec5bc188-bdbb-44f7-b10c-212477bb5490)

1. **Summary Report View**
![8](https://github.com/user-attachments/assets/58032917-42cd-4878-beed-90f7bb47bd12)

### 5.1 Create or Load a Test Plan

* Open JMeter GUI:

  ```bash
  jmeter
  ```

* **Design** test plan:

  1. **Thread Group**: Set number of threads (users), ramp-up, and loop count.
  2. **HTTP Request** sampler: Configure server, port, and path.
  3. **Listeners**: Add **View Results Tree**, **Summary Report**, **Aggregate Report**.

* **Save** your test as `benchmark_test.jmx`:

  ```text
  File ▶ Save Test Plan As ▶ benchmarking/benchmark_test.jmx
  ```

### 5.2 Run Tests in Non-GUI Mode

```bash
jmeter -n -t C:/Projects/BenchmarkingExample/jmeter/benchmark_test.jmx \
       -l C:/Projects/BenchmarkingExample/jmeter/results.jtl \
       -e -o C:/Projects/BenchmarkingExample/jmeter/reports
```

* `-n`: Non-GUI mode.
* `-t`: Test plan file.
* `-l`: Log file (`.jtl`).
* `-e`: Generate dashboard report.
* `-o`: Output folder for HTML report.

---

## 6. Generating & Viewing JMeter Results 📈

1. After test execution, open the **HTML dashboard** in your browser:

   ```text
   C:/Projects/BenchmarkingExample/jmeter/reports/index.html
   ```

2. Review metrics:

   * **Throughput**: Requests per second.
   * **Average** & **90th percentile** response times.
   * **Error %**: Failed requests.
   * **Graphs**: Response times over time.

3. **Export** specific tables as CSV for reports.

---

## ✅ Additional Tips

* **Network Emulation**: Use tools like **tc** (Linux) or **Clumsy** (Windows) to simulate latency/packet loss.
* **Distributed Testing**: Run JMeter in master-slave mode for large-scale tests.
* **CI Integration**: Automate benchmarks in Jenkins or GitHub Actions.
* **Baseline Comparisons**: Always compare against a known baseline to detect regressions.

---

> Your benchmarking journey starts here! 🚀 Adapt these steps to your environment and share your findings. Good luck! Cheers! 🎉

package com.example.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class HomeResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getHomePage() {
        String html = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Vedant Patel - Dropwizard on Azure</title>
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f4f4f4;
                        margin: 0;
                        padding: 0;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                    }

                    .container {
                        width: 90%;
                        max-width: 900px;
                        margin: auto;
                    }

                    header {
                        text-align: center;
                        margin-bottom: 20px;
                    }

                    h1 {
                        color: #2c3e50;
                        margin-bottom: 5px;
                    }

                    .subtitle {
                        font-size: 1.1em;
                        color: #666;
                    }

                    .card {
                        background-color: white;
                        padding: 20px;
                        border-radius: 12px;
                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                    }

                    .button-group {
                        margin-bottom: 20px;
                        display: flex;
                        justify-content: center;
                        gap: 10px;
                    }

                    button {
                        padding: 10px 20px;
                        font-size: 1em;
                        border: none;
                        border-radius: 5px;
                        background-color: #3498db;
                        color: white;
                        cursor: pointer;
                        transition: background-color 0.3s ease;
                    }

                    button:hover {
                        background-color: #2980b9;
                    }

                    #output {
                        background-color: #f9f9f9;
                        padding: 15px;
                        border: 1px solid #ddd;
                        border-radius: 5px;
                        height: 300px;
                        overflow-y: auto;
                        white-space: pre-wrap;
                    }

                    #status {
                        font-weight: bold;
                        margin-bottom: 10px;
                        color: #2c3e50;
                    }

                    footer {
                        text-align: center;
                        margin-top: 30px;
                        font-size: 0.9em;
                        color: #888;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <header>
                        <h1>JAVA 21 Dropwizard - VEDANT PATEL - 2.2</h1>
                        <p class="subtitle">
                            A production-ready Java service deployed on Azure App Service
                        </p>
                    </header>

                    <main class="card">
                        <div class="button-group">
                            <button onclick="callEndpoint('/log')">Test Logging</button>
                            <button onclick="callEndpoint('/env')">View Environment</button>
                            <button onclick="callEndpoint('/logs')">View Logs</button>
                        </div>

                        <div id="status"></div>

                        <pre id="output"></pre>
                    </main>

                    <footer>
                        <p>Deployed on Azure App Service (Linux & Windows) • Built with Dropwizard • Java 21 • GitHub Actions</p>
                    </footer>
                </div>

                <script>
                    function formatJson(json) {
                        try {
                            return JSON.stringify(JSON.parse(json), null, 2);
                        } catch (e) {
                            return json;
                        }
                    }

                    async function callEndpoint(endpoint) {
                        const output = document.getElementById('output');
                        const status = document.getElementById('status');

                        output.textContent = '';
                        status.textContent = 'Calling ' + endpoint + '...';

                        try {
                            const response = await fetch(endpoint);
                            const data = await response.text();

                            status.textContent = `Response from ${endpoint} (Status: ${response.status})`;

                            if (response.ok) {
                                output.textContent = formatJson(data);
                            } else {
                                output.textContent = `Error: ${response.status} - ${response.statusText}\\n\\n${data}`;
                            }
                        } catch (error) {
                            status.textContent = 'Failed to call endpoint';
                            output.textContent = 'Error: ' + error.message;
                        }
                    }
                </script>
            </body>
            </html>
        """;

        return Response.ok(html).build();
    }
}

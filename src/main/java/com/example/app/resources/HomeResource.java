package com.example.app.resources;

import javax.ws.rs.*;
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
                <title>Dropwizard App | Azure Deployment</title>
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
                <style>
                    :root {
                        --primary: #2563eb;
                        --primary-hover: #1d4ed8;
                        --background: #f8fafc;
                        --card: #ffffff;
                        --text: #1e293b;
                        --text-secondary: #64748b;
                        --border: #e2e8f0;
                        --error: #ef4444;
                        --code-bg: #f1f5f9;
                    }
                    
                    * {
                        box-sizing: border-box;
                        margin: 0;
                        padding: 0;
                    }
                    
                    body {
                        font-family: 'Inter', system-ui, -apple-system, sans-serif;
                        background-color: var(--background);
                        color: var(--text);
                        line-height: 1.5;
                        min-height: 100vh;
                        padding: 2rem 1rem;
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                    }
                    
                    .container {
                        width: 100%;
                        max-width: 800px;
                        margin: 0 auto;
                    }
                    
                    header {
                        text-align: center;
                        margin-bottom: 2.5rem;
                    }
                    
                    h1 {
                        font-size: 2rem;
                        font-weight: 600;
                        margin-bottom: 0.5rem;
                        color: var(--primary);
                    }
                    
                    .subtitle {
                        color: var(--text-secondary);
                        font-size: 1.1rem;
                        max-width: 600px;
                        margin: 0 auto;
                    }
                    
                    .card {
                        background-color: var(--card);
                        border-radius: 0.5rem;
                        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05), 0 1px 2px rgba(0, 0, 0, 0.1);
                        padding: 2rem;
                        margin-bottom: 2rem;
                        width: 100%;
                    }
                    
                    .button-group {
                        display: flex;
                        gap: 1rem;
                        flex-wrap: wrap;
                        margin-bottom: 1.5rem;
                        justify-content: center;
                    }
                    
                    button {
                        padding: 0.75rem 1.5rem;
                        font-size: 0.95rem;
                        font-weight: 500;
                        border: none;
                        border-radius: 0.375rem;
                        background-color: var(--primary);
                        color: white;
                        cursor: pointer;
                        transition: background-color 0.15s ease-in-out;
                    }
                    
                    button:hover {
                        background-color: var(--primary-hover);
                    }
                    
                    #status {
                        margin: 1rem 0;
                        font-size: 0.9rem;
                        color: var(--text-secondary);
                        text-align: center;
                        min-height: 1.5rem;
                    }
                    
                    #output {
                        padding: 1.25rem;
                        background-color: var(--code-bg);
                        border-radius: 0.375rem;
                        white-space: pre-wrap;
                        word-wrap: break-word;
                        font-family: 'Menlo', 'Consolas', monospace;
                        font-size: 0.875rem;
                        line-height: 1.7;
                    }
                    
                    #output:empty::before {
                        content: "Response will appear here...";
                        color: var(--text-secondary);
                        font-style: italic;
                    }
                    
                    footer {
                        margin-top: 3rem;
                        font-size: 0.875rem;
                        color: var(--text-secondary);
                        text-align: center;
                    }
                    
                    @media (max-width: 640px) {
                        .container {
                            padding: 0 1rem;
                        }
                        
                        .card {
                            padding: 1.5rem;
                        }
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <header>
                        <h1>JAVA 21 Dropwizard - VEDANT PATEL</h1>
                        <p class="subtitle">
                            A production-ready Java service deployed on Azure App Service
                        </p>
                    </header>
                    
                    <main class="card">
                        <div class="button-group">
                            <button onclick="callEndpoint('/log')">Test Logging</button>
                            <button onclick="callEndpoint('/env')">View Environment</button>
                        </div>
                        
                        <div id="status"></div>
                        
                        <pre id="output"></pre>
                    </main>
                    
                    <footer>
                        <p>Deployed on Azure App Service (Linux & Windows)• Built with Dropwizard • Java 21 • Github Action</p>
                    </footer>
                </div>

                <script>
                    function formatJson(json) {
                        try {
                            // Parse and re-stringify to ensure proper formatting
                            return JSON.stringify(JSON.parse(json), null, 2);
                        } catch (e) {
                            return json; // Return as-is if not JSON
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